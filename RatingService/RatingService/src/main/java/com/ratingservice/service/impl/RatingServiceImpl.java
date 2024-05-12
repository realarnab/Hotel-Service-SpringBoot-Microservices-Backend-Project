package com.ratingservice.service.impl;

import com.ratingservice.entity.Rating;
import com.ratingservice.payload.RatingDto;
import com.ratingservice.repository.RatingRepository;
import com.ratingservice.service.RatingService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RatingServiceImpl implements RatingService {

    private RatingRepository ratingRepository;
    private ModelMapper modelMapper;
    @Override
    public RatingDto create(RatingDto ratingDto) {
        Rating rating = mapToEntity(ratingDto);
        Rating saved = ratingRepository.save(rating);
        RatingDto dto = mapToDto(saved);
        return dto;
    }

    @Override
    public List<RatingDto> getRatings() {
        List<Rating> all = ratingRepository.findAll();
        List<RatingDto> dto = all.stream().map(e -> mapToDto(e)).collect(Collectors.toList());
        return dto;
    }

    @Override
    public List<RatingDto> getRatingByUserId(String userId) {
        List<Rating> byUserId = ratingRepository.findByUserId(userId);
        List<RatingDto> collect = byUserId.stream().map(e -> mapToDto(e)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<RatingDto> getRatingByHotelId(String hotelId) {
        List<Rating> byHotelId = ratingRepository.findByHotelId(hotelId);
        List<RatingDto> collect = byHotelId.stream().map(e -> mapToDto(e)).collect(Collectors.toList());
        return collect;
    }

    public Rating mapToEntity(RatingDto ratingDto){
        Rating rating = modelMapper.map(ratingDto, Rating.class);
        return rating;
    }

    public RatingDto mapToDto(Rating rating){
        RatingDto dto = modelMapper.map(rating, RatingDto.class);
        return dto;
    }
}
