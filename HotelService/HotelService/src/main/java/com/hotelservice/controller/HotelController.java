package com.hotelservice.controller;

import com.hotelservice.payload.HotelDto;
import com.hotelservice.service.HotelService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/hotels")
@Getter
@Setter
@AllArgsConstructor
public class HotelController {
    private HotelService hotelService;

    @PostMapping
    public ResponseEntity<HotelDto> createHotelReg(@RequestBody HotelDto hotelDto){
        HotelDto dto = hotelService.createHotel(hotelDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<HotelDto>> getAllHotels(){
        List<HotelDto> allHotels = hotelService.getAllHotels();
        return new ResponseEntity<>(allHotels,HttpStatus.OK);
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<HotelDto> getHotelById(@PathVariable String hotelId){
        HotelDto dto = hotelService.getHotelByHotelId(hotelId);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
}
