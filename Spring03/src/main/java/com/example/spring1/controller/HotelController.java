package com.example.spring1.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring1.entity.Hotel;
import com.example.spring1.service.HotelService;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @GetMapping("")
    public ResponseEntity<Map<String, Object>> queryHotelsByPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int rpp) {
        Map<String, Object> hotelPage = hotelService.getHotelV2(page, rpp);

        return ResponseEntity.ok(hotelPage);
    }
}
