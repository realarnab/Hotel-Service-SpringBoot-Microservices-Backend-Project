package com.hotelservice.service.impl;

import com.hotelservice.entity.Hotel;
import com.hotelservice.payload.HotelDto;
import com.hotelservice.repository.HotelRepository;
import com.hotelservice.service.HotelService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.management.modelmbean.ModelMBean;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Getter
@Setter
@AllArgsConstructor
public class HotelServiceImpl implements HotelService {

    private HotelRepository hotelRepository;
    private ModelMapper modelMapper;
    @Override
    public HotelDto createHotel(HotelDto hotelDto) {
        String s = UUID.randomUUID().toString();
        hotelDto.setId(s);
        Hotel hotel = mapToEntity(hotelDto);
        Hotel save = hotelRepository.save(hotel);
        HotelDto dto = mapToDto(save);
        return dto;
    }

    @Override
    public List<HotelDto> getAllHotels() {
        List<Hotel> all = hotelRepository.findAll();
        List<HotelDto> dto = all.stream().map(e -> mapToDto(e)).collect(Collectors.toList());
        return dto;
    }

    @Override
    public HotelDto getHotelByHotelId(String hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(() -> new RuntimeException("Not Found"));
        HotelDto hotelDto = mapToDto(hotel);
        return hotelDto;
    }

    public Hotel mapToEntity(HotelDto hotelDto) {
        Hotel hotel = modelMapper.map(hotelDto, Hotel.class);
        return hotel;
    }

    public HotelDto mapToDto(Hotel hotel){
        HotelDto dto = modelMapper.map(hotel, HotelDto.class);
        return dto;
    }
}
