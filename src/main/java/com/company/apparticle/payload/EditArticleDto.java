package com.company.apparticle.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record AddArticleDto(@NotBlank(message = "Title is required!") String title,
                            @NotNull(message = "Sections cannot be null") List<AddSectionDto> sections) {
}
