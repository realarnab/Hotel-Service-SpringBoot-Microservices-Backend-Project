package com.hotelservice.service;

import com.hotelservice.payload.HotelDto;

import java.util.List;

public interface HotelService {
    HotelDto createHotel(HotelDto hotelDto);
    List<HotelDto> getAllHotels();

    HotelDto getHotelByHotelId(String hotelId);
}
