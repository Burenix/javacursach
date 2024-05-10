package ru.edu.penzgtu.lab.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Schema(description = "Информация об законе")
public class LawDTO {
    @JsonProperty("id")
    @Schema(description = "ID закона в БД", example = "123")
    private Long id;

    @JsonProperty("name")
    @NotBlank
    @Schema(description = "Название закона", example = "Закон об образовании")
    private String name;

    @JsonProperty("dateSigned")
    @Schema(description = "Дата создания закона", example = "2052")
    private Long dateSigned;

    @JsonProperty("description")
    @NotBlank
    @Schema(description = "Описание закона", example = "Устанавливает правовые, организационные и экономические основы образования в Российской Федерации")
    private String description;

    @JsonProperty("liability")
    @NotBlank
    @Schema(description = "Ответственность за нарушение закона", example = "Уголовная")
    private String liability;

    @JsonProperty("dateAndTime")
    @Schema(description = "Дата и время добавления закона в БД")
    @NotNull
    private LocalDateTime localDateTime;

    @JsonProperty("president")
    @Schema(description = "Название призедента, издавшего закон")
    private String president;

}
