package com.company.apparticle.payload;

import jakarta.validation.constraints.NotBlank;

public record AddSectionDto(@NotBlank(message = "Title of section is required") String title,
                            @NotBlank(message = "Body of section is required") String body,
                            Long imageId) {
}
