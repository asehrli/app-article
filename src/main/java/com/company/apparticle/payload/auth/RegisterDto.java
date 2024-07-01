package com.company.apparticle.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterDto(
        @NotBlank(message = "Name is required") String name,
        @NotBlank(message = "Email is required") @Email(regexp = "^\\w+@\\w+\\.\\w+$", message = "Email is invalid") String email,
        @NotBlank(message = "Password is required") String password,
        @NotBlank(message = "Pre Password is required") String prePassword) {
}
