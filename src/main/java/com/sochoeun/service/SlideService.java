package com.sochoeun.service;

import com.sochoeun.entity.Slide;

import java.util.List;

public interface SlideService {
    Slide create(Slide slide);
    Slide getSlideById(Integer id);
    List<Slide> getAllSlides();
    void updateSlide(Integer id, Slide slide);
    void deleteSlide(Integer id);
}
