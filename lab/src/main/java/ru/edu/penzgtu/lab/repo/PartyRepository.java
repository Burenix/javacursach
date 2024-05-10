package ru.edu.penzgtu.lab.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.edu.penzgtu.lab.dto.PartyDTO;
import ru.edu.penzgtu.lab.entity.Law;
import ru.edu.penzgtu.lab.entity.Party;

import java.util.List;
import java.util.Optional;

public interface PartyRepository extends JpaRepository<Party, Long> {
    Optional<Party> findByName(String name);
}
