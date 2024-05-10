package ru.edu.penzgtu.lab.service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.edu.penzgtu.lab.dto.CountryDTO;
import ru.edu.penzgtu.lab.entity.Country;
import ru.edu.penzgtu.lab.entity.President;
import ru.edu.penzgtu.lab.repo.PresidentRepository;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryMapper {

    public List<CountryDTO> toListDTO(List<Country> countries) {
        return countries.stream().map(this::toDTO).toList();
    }

    public CountryDTO toDTO(Country country) {
        return CountryDTO.builder()
                .id(country.getId())
                .name(country.getName())
                .year(country.getYear())
                .continent(country.getContinent())
                .language(country.getLanguage())
                .localDateTime(country.getLocalDateTime())
                .presidents(country.getPresidents().stream()
                        .map(President::getName)
                        .toList())
                .build();
    }

    public Country toEntity(CountryDTO countryDTO) {
        Country country = new Country();

        country.setId(countryDTO.getId());
        country.setName(countryDTO.getName());
        country.setYear(countryDTO.getYear());
        country.setContinent(countryDTO.getContinent());
        country.setLanguage(countryDTO.getLanguage());
        country.setLocalDateTime(countryDTO.getLocalDateTime());

        return country;
    }
}
