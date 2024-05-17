package com.hotelservice.service.impl;

import com.hotelservice.entity.Staff;
import com.hotelservice.payload.StaffDto;
import com.hotelservice.repository.StaffRepository;
import com.hotelservice.service.StaffService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class StaffServiceImpl implements StaffService {
    private StaffRepository staffRepository;
    private ModelMapper modelMapper;

    public StaffServiceImpl(StaffRepository staffRepository,ModelMapper modelMapper) {
        this.staffRepository = staffRepository;
        this.modelMapper=modelMapper;
    }

    @Override
    public StaffDto createStaff(StaffDto staffDto) {
        Staff staff = mapToEntity(staffDto);
        Staff saved = staffRepository.save(staff);
        return mapToDto(saved);
    }

    public Staff mapToEntity(StaffDto staffDto){
        return modelMapper.map(staffDto,Staff.class);
    }

    public StaffDto mapToDto(Staff staff){
        return modelMapper.map(staff,StaffDto.class);
    }
}
