package ru.edu.penzgtu.lab.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.edu.penzgtu.lab.dto.LawDTO;
import ru.edu.penzgtu.lab.entity.Law;

import java.util.List;
import java.util.Optional;

public interface LawRepository extends JpaRepository<Law, Long> {
    Optional<Law> findByName(String name);
}
