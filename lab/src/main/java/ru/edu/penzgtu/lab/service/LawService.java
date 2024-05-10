package ru.edu.penzgtu.lab.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.edu.penzgtu.lab.dto.CountryDTO;
import ru.edu.penzgtu.lab.dto.LawDTO;
import ru.edu.penzgtu.lab.entity.Country;
import ru.edu.penzgtu.lab.entity.Law;
import ru.edu.penzgtu.lab.exception.ErrorType;
import ru.edu.penzgtu.lab.exception.PenzGtuException;
import ru.edu.penzgtu.lab.repo.LawRepository;
import ru.edu.penzgtu.lab.service.mapper.LawMapper;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LawService {
    private final LawRepository lawRepository;
    private final LawMapper lawMapper;

    public List<LawDTO> findAllLaws() {
        return lawMapper.toListDTO(lawRepository.findAll());
    }

    public LawDTO findLawById(Long id) {
        Law law = lawRepository.findById(id)
                .orElseThrow(() -> new PenzGtuException(ErrorType.NOT_FOUND, "Country not found"));

        return lawMapper.toDTO(law);
    }

    public void saveLaw(LawDTO lawDTO) {
        Law law = lawMapper.toEntity(lawDTO);
        law.setLocalDateTime(LocalDateTime.now());
        lawRepository.save(law);
    }

    public void updateLaw(LawDTO newLaw) {
        Law oldLaw = lawRepository.findById(newLaw.getId())
                .orElseThrow(() -> new PenzGtuException(ErrorType.NOT_FOUND, "Law not found"));

        oldLaw.setName(newLaw.getName());
        oldLaw.setDateSigned(newLaw.getDateSigned());
        oldLaw.setDescription(newLaw.getDescription());
        oldLaw.setLiability(newLaw.getLiability());

        lawRepository.save(oldLaw);
    }

    public void deleteLawById(Long id) {
        lawRepository.deleteById(id);
    }
}
