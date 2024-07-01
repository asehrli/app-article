package com.company.apparticle.payload;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class CodeDto {
    String code;
    final LocalDateTime createdAt;

    public CodeDto(String code) {
        this.code = code;
        createdAt = LocalDateTime.now();
    }
}
