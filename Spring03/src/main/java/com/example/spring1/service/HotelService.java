package com.example.spring1.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.spring1.entity.Hotel;
import com.example.spring1.repository.HotelRepository;

@Service
public class HotelService {
    @Autowired
    HotelRepository hotelRepository;

    public Page<Hotel> getHotel(int page, int rpp) {

        Pageable pageable = PageRequest.of(page, rpp); // 0 base
        Page<Hotel> hotelPage = hotelRepository.findAll(pageable);
        return hotelPage;
    }

    public Map<String, Object> getHotelV2(int page, int rpp) {

        Pageable pageable = PageRequest.of(page, rpp); // 0 base
        Page<Hotel> hotelPage = hotelRepository.findAll(pageable);
        Map<String, Object> map = new HashMap<>();
        map.put("isFirst", hotelPage.isFirst());
        map.put("isLast", hotelPage.isLast());
        map.put("page", hotelPage.getNumber());
        map.put("totalPage", hotelPage.getTotalPages());
        map.put("data", hotelPage.getContent());
        map.put("isEmpty", hotelPage.isEmpty());
        return map;
    }
}
