package com.emsi.donapp.service.impl;

import com.emsi.donapp.projection.CampagneResume;
import com.emsi.donapp.repository.CampagneJPA;
import com.emsi.donapp.service.CampagneService;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class CampagneServiceImpl implements CampagneService {
    private final CampagneJPA campagneRepository;

    public CampagneServiceImpl(CampagneJPA campagneRepository) {
        this.campagneRepository = campagneRepository;
    }

    @Override
    public List<CampagneResume> getActiveCampagnes() {
        LocalDate now = LocalDate.now();
        return campagneRepository.findByDateDebutLessThanEqualAndDateFinGreaterThanEqual(now, now, CampagneResume.class);
    }
}