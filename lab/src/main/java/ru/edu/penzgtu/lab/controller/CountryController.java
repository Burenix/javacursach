package ru.edu.penzgtu.lab.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.edu.penzgtu.lab.baseresponse.BaseResponseService;
import ru.edu.penzgtu.lab.baseresponse.ResponseWrapper;
import ru.edu.penzgtu.lab.dto.CountryDTO;
import ru.edu.penzgtu.lab.repo.CountryRepository;
import ru.edu.penzgtu.lab.service.CountryService;

import java.util.List;

@Validated
@RestController
@RequestMapping("/countries")
@RequiredArgsConstructor
@Tag(name = "Страны", description = "Операции над странами")
public class CountryController {
    private final CountryService countryService;
    private final BaseResponseService baseResponseService;

    @Operation(
            summary = "Получение всех стран", description = "Позволяет выгрузить все страны из БД"
    )

    @GetMapping
    public ResponseWrapper<List<CountryDTO>> findAll() {
        return baseResponseService.wrapSuccessResponse(countryService.findAllCountries());
    }

    @Operation(
            summary = "Получение страны по ID", description = "Позволяет выгрузить одну страну по ID из БД"
    )

    @GetMapping("/country/{id}")
    public ResponseWrapper<CountryDTO> getById(@PathVariable @Min(0) Long id) {
        return baseResponseService.wrapSuccessResponse(countryService.findCountryById(id));
    }

    @Operation(
            summary = "Получение страны по названию", description = "Позволяет выгрузить одну страну по названию из БД"
    )
    @GetMapping("/country/byName")
    public ResponseWrapper<List<CountryDTO>> findCountryByName(@RequestParam String name) {
        return baseResponseService.wrapSuccessResponse(countryService.findCountryByName(name));
    }


    @Operation(
            summary = "Создать страну", description = "Позволяет создать новую запись о стране в БД"
    )

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCountry(@RequestBody @Valid CountryDTO country) {
        countryService.saveCountry(country);
    }

    @Operation(
            summary = "Обновить данные о стране", description = "Позволяет обновить информацию о стране в БД"
    )

    @PutMapping("/country/")
    public void updateCountry(@RequestBody @Valid CountryDTO country) {
        countryService.updateCountry(country);
    }

    @Operation(
            summary = "Удалить страну по ID", description = "Позволяет удалить страну по ID из БД"
    )

    @DeleteMapping("/country/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCountry(@PathVariable @Min(0) Long id) {
        countryService.deleteCountryById(id);
    }
}
