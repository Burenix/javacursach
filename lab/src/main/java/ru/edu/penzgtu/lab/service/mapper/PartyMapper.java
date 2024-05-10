package ru.edu.penzgtu.lab.service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.edu.penzgtu.lab.dto.PartyDTO;
import ru.edu.penzgtu.lab.entity.Party;
import ru.edu.penzgtu.lab.entity.President;
import ru.edu.penzgtu.lab.repo.PresidentRepository;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PartyMapper {

    public List<PartyDTO> toListDTO(List<Party> parties) {
        return parties.stream().map(this::toDTO).toList();
    }

    public PartyDTO toDTO(Party party) {
        return PartyDTO.builder()
                .id(party.getId())
                .name(party.getName())
                .dateSigned(party.getDateSigned())
                .description(party.getDescription())
                .ideology(party.getIdeology())
                .localDateTime(party.getLocalDateTime())
                .presidents(party.getPresidents().stream()
                        .map(President::getName)
                        .toList())
                .build();
    }

    public Party toEntity(PartyDTO partyDTO) {
        Party party = new Party();

        party.setId(partyDTO.getId());
        party.setName(partyDTO.getName());
        party.setDateSigned(partyDTO.getDateSigned());
        party.setDescription(partyDTO.getDescription());
        party.setIdeology(partyDTO.getIdeology());
        party.setLocalDateTime(partyDTO.getLocalDateTime());

        return party;
    }
}
