package com.company.apparticle.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddCommentDto(@NotNull(message = "articleId is required!") Long articleId, @NotBlank(message = "Text is required") String text) {
}
