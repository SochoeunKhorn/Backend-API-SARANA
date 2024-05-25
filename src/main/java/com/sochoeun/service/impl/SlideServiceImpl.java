package com.sochoeun.service.impl;

import com.sochoeun.entity.Slide;
import com.sochoeun.exception.NotFoundException;
import com.sochoeun.repository.SlideRepository;
import com.sochoeun.service.SlideService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SlideServiceImpl implements SlideService {

    private final SlideRepository slideRepository;
    @Override
    public Slide create(Slide slide) {
        return slideRepository.save(slide);
    }

    @Override
    public Slide getSlideById(Integer id) {
        return slideRepository.findById(id).orElseThrow(()->new NotFoundException("Slide",id));
    }

    @Override
    public List<Slide> getAllSlides() {
        return slideRepository.findAll();
    }

    @Override
    public void updateSlide(Integer id, Slide slide) {
        Slide update = getSlideById(id);
        update.setName(slide.getName());
        update.setDescription(slide.getDescription());
        update.setImageUrl(slide.getImageUrl());
        slideRepository.save(update);
    }

    @Override
    public void deleteSlide(Integer id) {
        if(getSlideById(id) != null){
            slideRepository.deleteById(id);
        }
    }
}
