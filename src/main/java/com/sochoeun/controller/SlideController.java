package com.sochoeun.controller;

import com.sochoeun.entity.Slide;
import com.sochoeun.exception.response.BaseResponse;
import com.sochoeun.service.SlideService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/slides")
//@Tag(name = "Slides")
public class SlideController {
    private final SlideService slideService;
    private  BaseResponse baseResponse;
    @PostMapping
    public ResponseEntity<?> createSlide(@RequestBody Slide slide){
        baseResponse = new BaseResponse();
        Slide data = slideService.create(slide);
        baseResponse.createSuccess(data);
        return ResponseEntity.ok(baseResponse);
    }
    @Hidden
    @GetMapping("/{id}")
    public ResponseEntity<?> getSlideById(@PathVariable("id") Integer id){
        Slide slide = slideService.getSlideById(id);
        baseResponse.getSuccess(slide);
        return ResponseEntity.ok(baseResponse);
    }
    @GetMapping()
    public ResponseEntity<?> getAllSlides(){
        baseResponse = new BaseResponse();
        baseResponse.getSuccess(slideService.getAllSlides());
        return ResponseEntity.ok(baseResponse);
    }
    /*@Hidden
    @GetMapping(path = "/image/{filename}", produces = { IMAGE_PNG_VALUE, IMAGE_JPEG_VALUE })
    public byte[] getPhoto(@PathVariable("filename") String filename) throws IOException {
        return Files.readAllBytes(Paths.get(PHOTO_DIRECTORY + filename));
    }*/

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSlide(@PathVariable("id") Integer id, @RequestBody Slide slide){
        slideService.updateSlide(id,slide);
        baseResponse = new BaseResponse();
        baseResponse.updatedSuccess(slideService.getSlideById(id));
        return ResponseEntity.ok("updated");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSlide(@PathVariable("id") Integer id){
        slideService.deleteSlide(id);
        baseResponse = new BaseResponse();
        baseResponse.deletedSuccess();
        return ResponseEntity.ok(baseResponse);
    }

}
