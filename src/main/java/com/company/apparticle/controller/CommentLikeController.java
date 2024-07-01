package com.company.apparticle.controller;

import com.company.apparticle.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/article-like")
public interface ArticleLikeController {

    @PostMapping("/{articleId}")
    @ResponseStatus(HttpStatus.CREATED)
    ApiResponse<Boolean> like(@PathVariable Long articleId);

    @DeleteMapping("/{articleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void unlike(@PathVariable Long articleId);
}
