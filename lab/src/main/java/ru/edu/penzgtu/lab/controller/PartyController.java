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
import ru.edu.penzgtu.lab.dto.PartyDTO;
import ru.edu.penzgtu.lab.service.PartyService;

import java.util.List;

@Validated
@RestController
@RequestMapping("/parties")
@RequiredArgsConstructor
@Tag(name = "Партии", description = "Операции над партиями")
public class PartyController {
    private final PartyService partyService;
    private final BaseResponseService baseResponseService;

    @Operation(
            summary = "Получение всех партий", description = "Позволяет выгрузить все партии из БД"
    )

    @GetMapping
    public ResponseWrapper<List<PartyDTO>> findAll() {
        return baseResponseService.wrapSuccessResponse(partyService.findAllParties());
    }

    @Operation(
            summary = "Получение партии по ID", description = "Позволяет выгрузить одну партию по ID из БД"
    )

    @GetMapping("/parties/{id}")
    public ResponseWrapper<PartyDTO> getById(@PathVariable @Min(0) Long id) {
        return baseResponseService.wrapSuccessResponse(partyService.findPartyById(id));
    }

    @Operation(
            summary = "Получение партии по названию", description = "Позволяет выгрузить одну партию по названию из БД"
    )
    @GetMapping("/party/byName")
    public ResponseWrapper<List<PartyDTO>> findPartyByName(@RequestParam String name) {
        return baseResponseService.wrapSuccessResponse(partyService.findPartyByName(name));
    }



    @Operation(
            summary = "Создать партию", description = "Позволяет создать новую запись о партию в БД"
    )

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createParty(@RequestBody @Valid PartyDTO party) {
        partyService.saveParty(party);
    }

    @Operation(
            summary = "Обновить данные о партии", description = "Позволяет обновить информацию о партии в БД"
    )

    @PutMapping("/party/")
    public void updateParty(@RequestBody @Valid PartyDTO party) {
        partyService.updateParty(party);
    }

    @Operation(
            summary = "Удалить партию по ID", description = "Позволяет удалить партию по ID из БД"
    )

    @DeleteMapping("/party/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteParty(@PathVariable @Min(0) Long id) {
        partyService.deletePartyById(id);
    }
}
