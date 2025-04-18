package com.emsi.donapp.repository;

import com.emsi.donapp.model.Campagne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;
import java.util.List;

public interface CampagneJPA extends JpaRepository<Campagne, Long> {
    List<Campagne> findByDateDebutLessThanEqualAndDateFinGreaterThanEqual(LocalDate now1, LocalDate now2);
}