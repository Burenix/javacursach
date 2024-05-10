package ru.edu.penzgtu.lab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.edu.penzgtu.lab.dto.PresidentDTO;
import ru.edu.penzgtu.lab.entity.Country;
import ru.edu.penzgtu.lab.entity.Law;
import ru.edu.penzgtu.lab.entity.Party;
import ru.edu.penzgtu.lab.entity.President;
import ru.edu.penzgtu.lab.exception.ErrorType;
import ru.edu.penzgtu.lab.exception.PenzGtuException;
import ru.edu.penzgtu.lab.repo.CountryRepository;
import ru.edu.penzgtu.lab.repo.LawRepository;
import ru.edu.penzgtu.lab.repo.PartyRepository;
import ru.edu.penzgtu.lab.repo.PresidentRepository;
import ru.edu.penzgtu.lab.service.mapper.PresidentMapper;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PresidentService {
    private final PresidentRepository presidentRepository;
    private final PresidentMapper presidentMapper;
    private final LawRepository lawRepository;
    private final PartyRepository partyRepository;
    private final CountryRepository countryRepository;

    public President getByName(String name) {
        return presidentRepository.findByName(name)
                .orElseThrow(() -> new PenzGtuException(ErrorType.NOT_FOUND, "Not found by Name " + name));
    }

    public List<PresidentDTO> findAllPresidents() {
        return presidentMapper.toListDTO(presidentRepository.findAll());
    }

    public PresidentDTO findById(Long id) {
        President president = presidentRepository.findById(id)
                .orElseThrow(() -> new PenzGtuException(ErrorType.NOT_FOUND, "President not found"));
        return presidentMapper.toDTO(president);
    }

    public void savePresident(PresidentDTO presidentDTO) {
        President president = presidentMapper.toEntity(presidentDTO);
        president.setLocalDateTime(LocalDateTime.now());
        presidentRepository.save(president);

        Party party = partyRepository.findByName(presidentDTO.getPartyName()).get();
        Law law = lawRepository.findByName(presidentDTO.getLawName()).get();
        Country country = countryRepository.findByName(presidentDTO.getCountryName()).get();

        president.getParty().add(party);
        president.getLaw().add(law);
        president.getCountry().add(country);

        presidentRepository.save(president);
    }

    public void updatePresident(PresidentDTO newPresident) {
        President oldPresident = presidentRepository.findById(newPresident.getId())
                .orElseThrow(() -> new PenzGtuException(ErrorType.NOT_FOUND, "President not found"));

        oldPresident.setName(newPresident.getName());
        oldPresident.setYearsOld(newPresident.getYearsOld());
        oldPresident.setLanguage(newPresident.getLanguage());
        oldPresident.setTermStart(newPresident.getTermStart());

        presidentRepository.save(oldPresident);
    }

    public void deletePresidentById(Long id) {
        presidentRepository.deleteById(id);
    }
}

