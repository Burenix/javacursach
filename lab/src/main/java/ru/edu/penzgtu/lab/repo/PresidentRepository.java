package ru.edu.penzgtu.lab.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.edu.penzgtu.lab.dto.PresidentDTO;
import ru.edu.penzgtu.lab.entity.Law;
import ru.edu.penzgtu.lab.entity.Party;
import ru.edu.penzgtu.lab.entity.President;

import java.util.List;
import java.util.Optional;

@Repository
public interface PresidentRepository extends JpaRepository<President, Long> {
    Optional<President> findByName(String name);
}
