package com.example.spring1.apis;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Lab08")
public class Lab08 {

    @Autowired
    private NamedParameterJdbcTemplate jdbc;

    @RequestMapping("/test1")
    public void test1() {
        String sql = """
                INSERT INTO cust
                    (cname,tel,birthday)
                VALUES
                    (:cname,:tel,:birthday)
                """;
        HashMap<String, String> map = new HashMap<>();
        map.put("cname", "test111");
        map.put("tel", "123");
        map.put("birthday", "1911-01-02");

        int n = jdbc.update(sql, map);
        System.out.println(n);
    }
}
