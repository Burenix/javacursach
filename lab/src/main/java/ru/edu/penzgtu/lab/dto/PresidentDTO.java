package ru.edu.penzgtu.lab.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Информация о президенте")
public class PresidentDTO {

    @JsonProperty("id")
    @Schema(description = "ID призедента в БД", example = "123")
    private Long id;

    @JsonProperty("name")
    @NotBlank
    @Schema(description = "Имя и фамилия призедента", example = "Владимир Путин")
    private String name;

    @JsonProperty("yearsOld")
    @Schema(description = "Возраст призедента", example = "52")
    private Long yearsOld;

    @JsonProperty("language")
    @NotBlank
    @Schema(description = "Родной язык призедента", example = "Русский")
    private String language;

    @JsonProperty("termStart")
    @Schema(description = "Год начала правления призедента", example = "2052")
    private Long termStart;

    @JsonProperty("dateAndTime")
    @Schema(description = "Дата и время добавления художника в БД")
    @NotNull
    private LocalDateTime localDateTime;

    @JsonProperty("country")
    @Schema(description = "Название страны, принадлежащей призеденту")
    private String countryName;

    @JsonProperty("law")
    @Schema(description = "Название закона, который издал призедент")
    private String lawName;

    @JsonProperty("party")
    @Schema(description = "Название партии, которую создал призедент")
    private String partyName;

}
