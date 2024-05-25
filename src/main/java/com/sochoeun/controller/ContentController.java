package com.sochoeun.controller;

import com.sochoeun.entity.Content;
import com.sochoeun.exception.response.BaseResponse;
import com.sochoeun.pagination.PageResponse;
import com.sochoeun.service.ContentService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contents")
@RequiredArgsConstructor
@Tag(name = "Contents")
public class ContentController {
    private final ContentService contentService;
    private BaseResponse baseResponse;
    @PostMapping
    public ResponseEntity<?> createContent(@RequestBody Content content){
        Content saved = contentService.create(content);
        baseResponse = new BaseResponse();
        baseResponse.getSuccess(saved);
        return ResponseEntity.ok(baseResponse);
    }

    @GetMapping
    public ResponseEntity<?> getAllContents(){
        List<Content> contents = contentService.getContents();
        baseResponse = new BaseResponse();
        baseResponse.getSuccess(contents);
        return ResponseEntity.ok(baseResponse);
    }
    @Hidden
    @GetMapping("/{id}")
    public ResponseEntity<?> getContentById(@PathVariable("id")Integer id){
        Content getContent = contentService.getContent(id);
        baseResponse = new BaseResponse();
        baseResponse.getSuccess(getContent);
        return ResponseEntity.ok(baseResponse);
    }
    @GetMapping("/{id}/article")
    public ResponseEntity<?> getContentsPageable(@PathVariable("id")Integer id,
                                                 @RequestParam(value = "pageNo",required = false,defaultValue = "0") Integer pageNo,
                                                 @RequestParam(value = "pageSize",required = false,defaultValue = "5") Integer pageSize){
        Page<Content> pageable = contentService.getPageable(id, pageNo, pageSize);
        PageResponse pageResponse = new PageResponse(pageable);
        baseResponse = new BaseResponse();
        baseResponse.getSuccess(pageable);
        return ResponseEntity.ok(baseResponse);
    }
    @Hidden
    @GetMapping("/{id}/images")
    public ResponseEntity<?> getAllImagesContent(@PathVariable("id")Integer id){
        Content allImagesContent = contentService.getAllImagesContent(id);
        baseResponse = new BaseResponse();
        baseResponse.getSuccess(allImagesContent);
        return ResponseEntity.ok(baseResponse);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateContent(@PathVariable("id")Integer id, @RequestBody Content content){
        Content update = contentService.update(id, content);
        baseResponse = new BaseResponse();
        baseResponse.updatedSuccess(update);
        return ResponseEntity.ok(baseResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteContent(@PathVariable("id")Integer id){
        contentService.delete(id);
        baseResponse = new BaseResponse();
        baseResponse.deletedSuccess();
        return ResponseEntity.ok(baseResponse);
    }

}
