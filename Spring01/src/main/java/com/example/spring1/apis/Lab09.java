package com.example.spring1.apis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring1.dto.Member;
import com.example.spring1.dto.MeberResponse;
import com.example.spring1.utils.BCrypt;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/Lab09")
public class Lab09 {
    @Autowired
    private NamedParameterJdbcTemplate jdbc;
    // @Autowired
    // private MmeberResponse response;

    @PostMapping("/test1")
    public void test1(@RequestBody Member member) {
        System.out.println(member.getEmail());
        System.out.println(member.getPasswd());
        System.out.println(member.getName());
    }

    @PostMapping(value = { "/members", "/members/{isGetId}" })
    public MeberResponse addMember(@RequestBody @Valid Member member,
            @PathVariable(required = false) Boolean isGetId) {
        // System.out.println(member.getEmail());
        // System.out.println(member.getPasswd());
        // System.out.println(member.getName());

        isGetId = isGetId == null ? false : isGetId;

        String sql = """
                INSERT INTO member
                    (email,passwd,name)
                VALUES
                    (:email,:passwd,:name)
                """;
        HashMap<String, Object> map = new HashMap<>();
        map.put("email", member.getEmail());
        map.put("passwd", BCrypt.hashpw(member.getPasswd(), BCrypt.gensalt()));
        map.put("name", member.getName());

        // int n = jdbc.update(sql, map);
        // System.out.println(n);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int n = jdbc.update(sql, new MapSqlParameterSource(map), keyHolder);
        System.out.println(n);
        System.out.println(keyHolder.getKey().intValue());

        MeberResponse response = new MeberResponse();

        if (n > 0) {
            int lastId = keyHolder.getKey().intValue();
            if (isGetId) {
                member.setId(lastId);
                response.setInsertId(lastId);
            }

            response.setError(0);
            response.setMessage("新增成功");
            response.setMember(member);

        } else {
            response.setError(-1);
            response.setMessage("新增失敗");
        }

        return response;
    }

    @PostMapping("/members/list")
    public void addMembers(@RequestBody List<Member> members) {

        String sql = """
                INSERT INTO member
                    (email,passwd,name)
                VALUES
                    (:email,:passwd,:name)
                """;

        MapSqlParameterSource[] params = new MapSqlParameterSource[members.size()];

        for (int i = 0; i < members.size(); i++) {
            params[i] = new MapSqlParameterSource();
            params[i].addValue("email", members.get(i).getEmail());
            params[i].addValue("passwd", BCrypt.hashpw(members.get(i).getPasswd(), BCrypt.gensalt()));
            params[i].addValue("name", members.get(i).getName());
        }

        // int[] r = jdbc.batchUpdate(sql, params);
        // for (int v : r) {
        // System.out.println(v);
        // }
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.batchUpdate(sql, params, keyHolder);

        List<Map<String, Object>> maps = keyHolder.getKeyList();
        for (Map<String, Object> map : maps) {
            // Set<String> keys = map.keySet();
            // for (String key : keys) {
            // System.out.printf("%s:%s\n", key, map.get(key));
            // }
            // System.out.println("----------");

            System.out.println(map.get("GENERATED_KEY"));
        }
    }

    @PostMapping("/members/listv2")
    public void addMembersV2(@RequestBody List<Member> members) {
        for (Member member : members) {
            addMember(member, true);
        }
    }

    @DeleteMapping("/members/{id}")
    public MeberResponse delete(@PathVariable Integer id) {
        String sql = """
                DELETE FROM member
                WHERE id = :id
                """;
        HashMap<String, Integer> map = new HashMap<>();
        map.put("id", id);
        int n = jdbc.update(sql, map);
        MeberResponse response = new MeberResponse();
        if (n > 0) {
            response.setError(0);
            response.setMessage("刪除成功");
        } else {
            response.setError(-1);
            response.setMessage("刪除失敗");
        }

        return response;

    }

    @PutMapping("members")
    public MeberResponse update(@RequestBody Member member) {
        MeberResponse response = new MeberResponse();
        String sql;
        HashMap<String, Object> params = new HashMap<>();
        if (member.getPasswd() != null) {
            sql = """
                    UPDATE member
                    SET name=:name,
                        passwd=:passwd
                    WHERE
                        id=:id
                    """;
            params.put("name", member.getName());
            params.put("passwd", BCrypt.hashpw(member.getPasswd(), BCrypt.gensalt()));
            params.put("id", member.getId());
        } else {
            sql = """
                    UPDATE member
                    SET name=:name,
                    WHERE
                        id=:id
                    """;
            params.put("name", member.getName());
            params.put("id", member.getId());
        }
        int n = jdbc.update(sql, params);
        if (n > 0) {
            response.setError(0);
            response.setMessage("修改成功");
        } else {
            response.setError(-1);
            response.setMessage("修改失敗");
        }

        return response;
    }
}
