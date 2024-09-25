package com.example.springboot.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookPayloadDTO {
    @NotBlank // 유효성 검사 체크
    @Schema(description = "책 제목",
            example = "Spring 정복하기",
            requiredMode = Schema.RequiredMode.REQUIRED) // 필수
    private String title;

    @NotBlank
    private int price;
    @NotBlank
    private String author;
    @NotBlank
    private int page;
}
