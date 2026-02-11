package com.example.spring1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring1.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

}
