package com.company.apparticle.controller;

import com.company.apparticle.payload.ApiResponse;
import com.company.apparticle.service.ArticleLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ArticleLikeControllerImpl implements ArticleLikeController {
    private final ArticleLikeService articleLikeService;

    @Override
    public ApiResponse<Boolean> like(Long articleId) {
        return articleLikeService.like(articleId);
    }

    @Override
    public void unlike(Long articleId) {
        articleLikeService.unlike(articleId);
    }
}
