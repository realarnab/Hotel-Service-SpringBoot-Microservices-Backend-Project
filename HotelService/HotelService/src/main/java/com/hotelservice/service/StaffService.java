package com.hotelservice.service;

import com.hotelservice.payload.StaffDto;

import java.util.List;

public interface StaffService {
    public StaffDto createStaff(StaffDto staffDto);
    List<StaffDto> getAll();
}
