package com.example.spring1.apis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring1.dto.Hotel;
import com.example.spring1.dto.HotelRowMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/hotels")
public class Lab11 {

    @Autowired
    NamedParameterJdbcTemplate jdbc;

    @Autowired
    HotelRowMapper hotelRowMapper;

    @RequestMapping("/test1")
    public List<Hotel> test1() {
        String sql = "SELECT id,name,addr,tel FROM hotel";
        List<Hotel> hotels = jdbc.query(sql, hotelRowMapper);
        // System.out.println(hotels.size());
        // for (Hotel hotel : hotels) {
        // System.out.printf("%d.%s:%s:%s\n", hotel.getId(), hotel.getName(),
        // hotel.getAddr(), hotel.getTel());
        // }
        return hotels;
    }

    @GetMapping("/{hotelId}")
    public Hotel findById(@PathVariable Long hotelId) {
        String sql = """
                SELECT id,name,addr,tel
                FROM hotel
                WHERE id=:id
                """;

        Map<String, Long> param = new HashMap<>();
        param.put("id", hotelId);
        List<Hotel> hotels = jdbc.query(sql, param, hotelRowMapper);
        if (hotels.size() > 0) {
            return hotels.get(0);
        } else {
            Hotel hotel = new Hotel();
            hotel.setError(-1);
            return hotel;
        }
    }

    @GetMapping("/v2/{hotelId}")
    public Hotel findByIdV2(@PathVariable Long hotelId) {
        String sql = """
                SELECT id,name,addr,tel
                FROM hotel
                WHERE id=:id
                """;

        Map<String, Long> param = new HashMap<>();
        param.put("id", hotelId);

        Hotel hotel;
        try {
            hotel = jdbc.queryForObject(sql, param, hotelRowMapper);
        } catch (Exception e) {
            System.out.println(e);
            hotel = new Hotel();
            hotel.setError(-1);
        }
        return hotel;

    }

    @GetMapping("/search")
    public List<Hotel> findByKey(@RequestParam String key) {
        String sql = """
                SELECT id,name,addr,tel
                FROM hotel
                WHERE name LIKE :skey OR addr LIKE :skey OR tel LIKE :skey
                """;
        Map<String, String> params = new HashMap<>();
        params.put("skey", "%" + key + "%");
        return jdbc.query(sql, params, hotelRowMapper);
        // if (hotels.size() > 0) {

        // } else {

        // }
        // return hotels;
    }

}
