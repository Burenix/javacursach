package ru.edu.penzgtu.lab.service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.edu.penzgtu.lab.dto.PresidentDTO;
import ru.edu.penzgtu.lab.entity.Country;
import ru.edu.penzgtu.lab.entity.President;
import ru.edu.penzgtu.lab.repo.CountryRepository;
import ru.edu.penzgtu.lab.repo.LawRepository;
import ru.edu.penzgtu.lab.repo.PartyRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PresidentMapper {
    public List<PresidentDTO> toListDTO(List<President> presidents) {
        return presidents.stream().map(this::toDTO).toList();
    }

    public PresidentDTO toDTO(President president) {
        String countryName = president.getCountry() != null ?
                president.getCountry().getName() : null;
        String lawName = president.getLaw() != null ?
                president.getLaw().getName() : null;
        String partyName = president.getLaw() != null ?
                president.getLaw().getName() : null;
        return PresidentDTO.builder()
                .id(president.getId())
                .name(president.getName())
                .yearsOld(president.getYearsOld())
                .language(president.getLanguage())
                .termStart(president.getTermStart())
                .localDateTime(president.getLocalDateTime())
                .countryName(countryName)
                .lawName(lawName)
                .partyName(partyName)
                .build();
    }

    public President toEntity(PresidentDTO presidentDTO) {
        President president = new President();

        president.setId(presidentDTO.getId());
        president.setName(presidentDTO.getName());
        president.setYearsOld(presidentDTO.getYearsOld());
        president.setLanguage(presidentDTO.getLanguage());
        president.setTermStart(presidentDTO.getTermStart());
        president.setLocalDateTime(presidentDTO.getLocalDateTime());

        return president;
    }
}
