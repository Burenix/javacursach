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
import ru.edu.penzgtu.lab.dto.PresidentDTO;
import ru.edu.penzgtu.lab.service.PresidentService;

import java.util.List;

@Validated
@RestController
@RequestMapping("/presidents")
@RequiredArgsConstructor
@Tag(name = "Президенты", description = "Операции над президентами")
public class PresidentController {
    private final PresidentService presidentService;
    private final BaseResponseService baseResponseService;

    @Operation(
            summary = "Получение всех президентов", description = "Позволяет выгрузить всех президентов из БД"
    )

    @GetMapping
    public ResponseWrapper<List<PresidentDTO>> findAll() {
        return baseResponseService.wrapSuccessResponse(presidentService.findAllPresidents());
    }

    @Operation(
            summary = "Получение президентов по ID", description = "Позволяет выгрузить одного президента по ID из БД"
    )

    @GetMapping("/president/{id}")
    public ResponseWrapper<PresidentDTO> getById(@PathVariable @Min(0) Long id) {
        return baseResponseService.wrapSuccessResponse(presidentService.findPresidentById(id));
    }

    @Operation(
            summary = "Получение президента по названию", description = "Позволяет выгрузить одного президента по названию из БД"
    )
    @GetMapping("/president/byName")
    public ResponseWrapper<List<PresidentDTO>> findPresidentByName(@RequestParam String name) {
        return baseResponseService.wrapSuccessResponse(presidentService.findPresidentByName(name));
    }


    @Operation(
            summary = "Создать президента", description = "Позволяет создать новую запись о президенте в БД"
    )

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createPresident(@RequestBody @Valid PresidentDTO president) {
        presidentService.savePresident(president);
    }

    @Operation(
            summary = "Обновить данные о президенте", description = "Позволяет обновить информацию о президенте в БД"
    )

    @PutMapping("/president/")
    public void updatePresident(@RequestBody @Valid PresidentDTO president) {
        presidentService.updatePresident(president);
    }

    @Operation(
            summary = "Удалить президента по ID", description = "Позволяет удалить президента по ID из БД"
    )

    @DeleteMapping("/president/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePresident(@PathVariable @Min(0) Long id) {
        presidentService.deletePresidentById(id);
    }
}
