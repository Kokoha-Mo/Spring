package com.example.spring1.apis;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.spring1.dto.Hotel;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/Lab10")
public class Lab10 {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private NamedParameterJdbcTemplate jdbc;

    @RequestMapping("/test1")
    public void test1() {
        jdbc.update("DELETE FROM hotel", new HashMap<>());
        jdbc.update("ALTER TABLE hotel auto_increment = 1", new HashMap<>());
        String url = "https://data.moa.gov.tw/Service/OpenData/ODwsv/ODwsvTravelStay.aspx";

        String json = restTemplate.getForObject(url, String.class);
        // System.out.println(json);

        ObjectMapper mapper = new ObjectMapper();
        List<Hotel> hotels = mapper.readValue(json, new TypeReference<List<Hotel>>() {
        });
        // System.out.println(hotels.size());

        String sql = """
                INSERT INTO hotel
                    (name,addr,tel)
                VALUES
                    (:name,:addr,:tel)
                """;
        MapSqlParameterSource[] params = new MapSqlParameterSource[hotels.size()];
        for (int i = 0; i < hotels.size(); i++) {
            params[i] = new MapSqlParameterSource();
            params[i].addValue("name", hotels.get(i).getName());
            params[i].addValue("addr", hotels.get(i).getAddr());
            params[i].addValue("tel", hotels.get(i).getTel());
        }

        jdbc.batchUpdate(sql, params);
        System.out.println("FINISH");

    }

    @RequestMapping("/test2")
    public void test2() {
        String url = "https://data.moa.gov.tw/Service/OpenData/ODwsv/ODwsvTravelStay.aspx";

        String json = restTemplate.getForObject(url, String.class);
        ObjectMapper mapper = new ObjectMapper();
        Hotel[] hotels = mapper.readValue(json, new TypeReference<Hotel[]>() {
        });

        for (Hotel hotel : hotels) {
            System.out.printf("%s:%s:%s\n", hotel.getName(), hotel.getAddr(), hotel.getTel());
        }

    }
}
