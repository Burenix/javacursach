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
@Schema(description = "Информация об стране")
public class CountryDTO {

    @JsonProperty("id")
    @Schema(description = "ID страны в БД", example = "123")
    private Long id;

    @JsonProperty("name")
    @NotBlank
    @Schema(description = "Название страны", example = "Россия")
    private String name;

    @JsonProperty("year")
    @Schema(description = "Год основания страны", example = "2052")
    private Long year;

    @JsonProperty("continent")
    @NotBlank
    @Schema(description = "Континент, в которой находиться страна", example = "Евразия")
    private String continent;

    @JsonProperty("language")
    @NotBlank
    @Schema(description = "Государственный язык", example = "Русский")
    private String language;

    @JsonProperty("dateAndTime")
    @Schema(description = "Дата и время добавления художника в БД")
    @NotNull
    private LocalDateTime localDateTime;

    @JsonProperty("presidents")
    @Schema(description = "Название призедентов страны")
    private List<String> presidents;

}
