package com.sochoeun.service.impl;

import com.sochoeun.entity.Article;
import com.sochoeun.entity.Category;
import com.sochoeun.exception.NotFoundException;
import com.sochoeun.pagination.PageUtil;
import com.sochoeun.repository.ArticleRepository;
import com.sochoeun.service.ArticleService;
import com.sochoeun.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final CategoryService categoryService;
    @Override
    public Article createOrUpdate(Article article) {
        Category category = categoryService.getCategory(article.getCategory().getId());
        Integer id = article.getId();
        if(id != 0){
            Article update = new Article();
            update.setCategory(category);
            update.setName(article.getName());
            return articleRepository.save(update);
        }
        return articleRepository.save(article);
    }

    @Override
    public List<Article> getArticles() {
        return articleRepository.findAll();
    }

    @Override
    public Page<Article> getArticlesByCategoryId(Integer categoryId,Integer pageNo,Integer pageSize) {
        Pageable pageable = PageUtil.getPageable(pageNo,pageSize);
        return articleRepository.findAllByCategory_Id(categoryId,pageable);
    }

    @Override
    public Article getArticle(Integer id) {
        return articleRepository.findById(id).orElseThrow(()->new NotFoundException("Article",id));
    }

    @Override
    public void delete(Integer id) {
        getArticle(id);
        articleRepository.deleteById(id);
    }
}
