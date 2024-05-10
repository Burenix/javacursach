package ru.edu.penzgtu.lab.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.edu.penzgtu.lab.dto.PartyDTO;
import ru.edu.penzgtu.lab.entity.Law;
import ru.edu.penzgtu.lab.entity.Party;
import ru.edu.penzgtu.lab.exception.ErrorType;
import ru.edu.penzgtu.lab.exception.PenzGtuException;
import ru.edu.penzgtu.lab.repo.PartyRepository;
import ru.edu.penzgtu.lab.service.mapper.PartyMapper;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PartyService {
    private final PartyRepository partyRepository;
    private final PartyMapper partyMapper;

    public List<PartyDTO> findAllParties() {
        return partyMapper.toListDTO(partyRepository.findAll());
    }

    public PartyDTO findPartyById(Long id) {
        Party party = partyRepository.findById(id)
                .orElseThrow(() -> new PenzGtuException(ErrorType.NOT_FOUND, "Party not found"));

        return partyMapper.toDTO(party);
    }

    public void saveParty(PartyDTO partyDTO) {
        Party party = partyMapper.toEntity(partyDTO);
        party.setLocalDateTime(LocalDateTime.now());
        partyRepository.save(party);
    }

    public void updateParty(PartyDTO newParty) {
        Party oldParty = partyRepository.findById(newParty.getId())
                .orElseThrow(() -> new PenzGtuException(ErrorType.NOT_FOUND, "Party not found"));

        oldParty.setName(newParty.getName());
        oldParty.setDateSigned(newParty.getDateSigned());
        oldParty.setDescription(newParty.getDescription());
        oldParty.setIdeology(newParty.getIdeology());

        partyRepository.save(oldParty);
    }

    public void deletePartyById(Long id) {
        partyRepository.deleteById(id);
    }
}
