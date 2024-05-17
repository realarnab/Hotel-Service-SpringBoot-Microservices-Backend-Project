package com.hotelservice.controller;

import com.hotelservice.payload.StaffDto;
import com.hotelservice.service.StaffService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staffs")
public class StaffController {

    private StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping
    public ResponseEntity<?> getStaffs(){
        List<StaffDto> all = staffService.getAll();
        return  new ResponseEntity<>(all, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createStaff(@RequestBody StaffDto staffDto){
        StaffDto staff = staffService.createStaff(staffDto);
        return new ResponseEntity<>(staff,HttpStatus.CREATED);
    }
}
