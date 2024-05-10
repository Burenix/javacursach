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
@Schema(description = "Информация об партии")
public class PartyDTO {
    @JsonProperty("id")
    @Schema(description = "ID партии в БД", example = "123")
    private Long id;

    @JsonProperty("name")
    @NotBlank
    @Schema(description = "Название партии", example = "Либеральная")
    private String name;

    @JsonProperty("dateSigned")
    @Schema(description = "Дата создания партии", example = "2052")
    private Long dateSigned;

    @JsonProperty("description")
    @NotBlank
    @Schema(description = "Описание партии", example = "Политическая партия, придерживающаяся идеологии либерализма")
    private String description;

    @JsonProperty("ideology")
    @NotBlank
    @Schema(description = "Идеология партии", example = "Либерализм")
    private String ideology;

    @JsonProperty("dateAndTime")
    @Schema(description = "Дата и время добавления партии в БД")
    @NotNull
    private LocalDateTime localDateTime;

    @JsonProperty("presidents")
    @Schema(description = "Название призедентов, создавших партию")
    private List<String> presidents;

}
