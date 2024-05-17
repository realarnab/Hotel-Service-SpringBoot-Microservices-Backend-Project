package com.hotelservice.service.impl;

import com.hotelservice.entity.Staff;
import com.hotelservice.payload.StaffDto;
import com.hotelservice.repository.StaffRepository;
import com.hotelservice.service.StaffService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<StaffDto> getAll() {
        List<Staff> all = staffRepository.findAll();
        List<StaffDto> staffs = all.stream().map((element) -> modelMapper.map(element, StaffDto.class)).collect(Collectors.toList());
        return staffs;
    }

    public Staff mapToEntity(StaffDto staffDto){
        return modelMapper.map(staffDto,Staff.class);
    }

    public StaffDto mapToDto(Staff staff){
        return modelMapper.map(staff,StaffDto.class);
    }
}
