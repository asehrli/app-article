package com.company.apparticle.service;

import com.company.apparticle.component.Auditing;
import com.company.apparticle.entity.Article;
import com.company.apparticle.entity.ArticleLike;
import com.company.apparticle.entity.User;
import com.company.apparticle.exception.MyConflictException;
import com.company.apparticle.exception.MyNotFoundException;
import com.company.apparticle.payload.ApiResponse;
import com.company.apparticle.repository.ArticleLikeRepository;
import com.company.apparticle.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleCommentServiceImpl implements ArticleCommentService {
    private final ArticleCommentRepository articleCommentRepository;
    private final Auditing auditing;
    private final ArticleRepository articleRepository;

    @Override
    public ApiResponse<Boolean> like(Long articleId) {
        User user = auditing.getCurrentAuditor().orElseThrow();
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new MyNotFoundException("Article not found!"));
        articleLikeRepository.findByArticleIdAndCreatedById(articleId, user.getId()).ifPresent((a) -> {
            throw new MyConflictException("Already liked!");
        });

        articleLikeRepository.save(ArticleLike.builder().article(article).build());
        return ApiResponse.success(true);
    }

    @Override
    public void unlike(Long articleId) {
        User user = auditing.getCurrentAuditor().orElseThrow();
        articleRepository.findById(articleId).orElseThrow(() -> new MyNotFoundException("Article not found!"));
        ArticleLike articleLike = articleLikeRepository.findByArticleIdAndCreatedById(articleId, user.getId()).orElseThrow(() -> new MyConflictException("Already liked!"));
        articleLikeRepository.delete(articleLike);
    }
}
