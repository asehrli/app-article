package com.company.apparticle.payload;

import jakarta.validation.constraints.NotBlank;

public record ResetPasswordDto(@NotBlank(message = "Token is required") String token,
                               @NotBlank(message = "Password is required") String password,
                               @NotBlank(message = "Pre Password is required") String prePassword) {
}
