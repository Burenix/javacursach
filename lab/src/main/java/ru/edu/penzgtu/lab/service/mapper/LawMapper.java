package ru.edu.penzgtu.lab.service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.edu.penzgtu.lab.dto.CountryDTO;
import ru.edu.penzgtu.lab.dto.LawDTO;
import ru.edu.penzgtu.lab.entity.Law;
import ru.edu.penzgtu.lab.entity.President;
import ru.edu.penzgtu.lab.repo.PresidentRepository;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LawMapper {

    public List<LawDTO> toListDTO(List<Law> laws) {
        return laws.stream().map(this::toDTO).toList();
    }

    public LawDTO toDTO(Law law) {
        return LawDTO.builder()
                .id(law.getId())
                .name(law.getName())
                .dateSigned(law.getDateSigned())
                .description(law.getDescription())
                .liability(law.getLiability())
                .localDateTime(law.getLocalDateTime())
                .president(law.getPresident().getName())
                .build();
    }

    public Law toEntity(LawDTO lawDTO) {
        Law law = new Law();

        law.setId(lawDTO.getId());
        law.setName(lawDTO.getName());
        law.setDateSigned(lawDTO.getDateSigned());
        law.setDescription(lawDTO.getDescription());
        law.setLiability(lawDTO.getLiability());
        law.setLocalDateTime(lawDTO.getLocalDateTime());

        return law;
    }
}
