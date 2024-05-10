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
import ru.edu.penzgtu.lab.dto.LawDTO;
import ru.edu.penzgtu.lab.service.LawService;

import java.util.List;

@Validated
@RestController
@RequestMapping("/laws")
@RequiredArgsConstructor
@Tag(name = "Законы", description = "Операции над законами")
public class LawController {
    private final LawService lawService;
    private final BaseResponseService baseResponseService;

    @Operation(
            summary = "Получение всех законов", description = "Позволяет выгрузить все законы из БД"
    )

    @GetMapping
    public ResponseWrapper<List<LawDTO>> findAll() {
        return baseResponseService.wrapSuccessResponse(lawService.findAllLaws());
    }

    @Operation(
            summary = "Получение закона по ID", description = "Позволяет выгрузить один закон по ID из БД"
    )

    @GetMapping("/laws/{id}")
    public ResponseWrapper<LawDTO> getById(@PathVariable @Min(0) Long id) {
        return baseResponseService.wrapSuccessResponse(lawService.findLawById(id));
    }

    @Operation(
            summary = "Получение закона по названию", description = "Позволяет выгрузить один закон по названию из БД"
    )
    @GetMapping("/law/byName")
    public ResponseWrapper<List<LawDTO>> findLawByName(@RequestParam String name) {
        return baseResponseService.wrapSuccessResponse(lawService.findLawByName(name));
    }

    @Operation(
            summary = "Создать закон", description = "Позволяет создать новую запись о законе в БД"
    )

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createLaw(@RequestBody @Valid LawDTO law) {
        lawService.saveLaw(law);
    }

    @Operation(
            summary = "Обновить данные о законе", description = "Позволяет обновить информацию о законе в БД"
    )

    @PutMapping("/law/")
    public void updateLaw(@RequestBody @Valid LawDTO law) {
        lawService.updateLaw(law);
    }

    @Operation(
            summary = "Удалить закон по ID", description = "Позволяет удалить закон по ID из БД"
    )

    @DeleteMapping("/law/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLaw(@PathVariable @Min(0) Long id) {
        lawService.deleteLawById(id);
    }
}
