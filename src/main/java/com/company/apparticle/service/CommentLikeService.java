package com.company.apparticle.service;

import com.company.apparticle.payload.ApiResponse;

public interface ArticleCommentService {
    ApiResponse<Boolean> like(Long commentId);
    void unlike(Long commentId);
}
