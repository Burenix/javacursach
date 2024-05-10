package ru.edu.penzgtu.lab.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.edu.penzgtu.lab.dto.CountryDTO;
import ru.edu.penzgtu.lab.entity.Country;
import ru.edu.penzgtu.lab.exception.ErrorType;
import ru.edu.penzgtu.lab.exception.PenzGtuException;
import ru.edu.penzgtu.lab.repo.CountryRepository;
import ru.edu.penzgtu.lab.service.mapper.CountryMapper;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CountryService {
    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    @Transactional
    public List<CountryDTO> findAllCountries() {
        return countryMapper.toListDTO(countryRepository.findAll());
    }

    public CountryDTO findCountryById(Long id) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new PenzGtuException(ErrorType.NOT_FOUND, "Country not found"));

        return countryMapper.toDTO(country);
    }

    public void saveCountry(CountryDTO countryDTO) {
        Country country = countryMapper.toEntity(countryDTO);
        country.setLocalDateTime(LocalDateTime.now());
        countryRepository.save(country);
    }

    public void updateCountry(CountryDTO newCountry) {
        Country oldCountry = countryRepository.findById(newCountry.getId())
                .orElseThrow(() -> new PenzGtuException(ErrorType.NOT_FOUND, "Country not found"));

        oldCountry.setName(newCountry.getName());
        oldCountry.setYear(newCountry.getYear());
        oldCountry.setContinent(newCountry.getContinent());
        oldCountry.setLanguage(newCountry.getLanguage());

        countryRepository.save(oldCountry);
    }

    public void deleteCountryById(Long id) {
        countryRepository.deleteById(id);
    }
}
