package com.sochoeun.controller;

import com.sochoeun.entity.Article;
import com.sochoeun.exception.response.BaseResponse;
import com.sochoeun.pagination.PageResponse;
import com.sochoeun.service.ArticleService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/articles")
@Tag(name = "Articles")
public class ArticleController {
    private final ArticleService articleService;
    private BaseResponse baseResponse;
    @PostMapping
    public ResponseEntity<?> createArticle(@RequestBody Article article){
        Article create = articleService.createOrUpdate(article);
        baseResponse = new BaseResponse();
        baseResponse.createSuccess(create);
        return ResponseEntity.ok(article);
    }
    @GetMapping
    public ResponseEntity<?> getAllArticles(){
        List<Article> articles = articleService.getArticles();
        baseResponse = new BaseResponse();
        baseResponse.getSuccess(articles);
        return ResponseEntity.ok(baseResponse);
    }
    
    @Hidden
    @GetMapping("/category/{category_id}")
    public ResponseEntity<?> getArticlesByCategoryId(@PathVariable("category_id") Integer category_id,
                                                     @RequestParam(value = "pageNo",required = false,defaultValue = "0") Integer pageNo,
                                                     @RequestParam(value = "pageSize",required = false,defaultValue = "5") Integer pageSize){
        Page<Article>page=articleService.getArticlesByCategoryId(category_id,pageNo,pageSize);
        PageResponse pageResponse = new PageResponse(page);
        baseResponse = new BaseResponse();
        baseResponse.getSuccess(pageResponse);
        return ResponseEntity.ok(baseResponse);
    }
    @Hidden
    @GetMapping("/{id}")
    public ResponseEntity<?> getArticleById(@PathVariable("id") Integer id){
        Article article = articleService.getArticle(id);
        baseResponse = new BaseResponse();
        return ResponseEntity.ok(baseResponse);
    }
    @PutMapping()
    public ResponseEntity<?> updateArticle(@RequestBody Article article){
        Article updated = articleService.createOrUpdate(article);
        baseResponse = new BaseResponse();
        baseResponse.updatedSuccess(updated);
        return ResponseEntity.ok(baseResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable("id") Integer id){
        articleService.delete(id);
        baseResponse = new BaseResponse();
        baseResponse.deletedSuccess();
        return ResponseEntity.ok(baseResponse);
    }
}
