package com.emsi.donapp.service;

import com.emsi.donapp.projection.CampagneResume;
import com.emsi.donapp.repository.CampagneJPA;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CampagneServiceTest {
    @Mock
    private CampagneJPA campagneRepository;

    @InjectMocks
    private CampagneServiceImpl campagneService;

    @Test
    void getActiveCampagnes_ShouldReturnActiveCampagnes() {
        LocalDate now = LocalDate.now();
        CampagneResume res1 = new CampagneResume() {
            public Long getId() { return 1L; }
            public String getNom() { return "Campagne 1"; }
            public BigDecimal getObjectifMontant() { return new BigDecimal("10000.00"); }
        };

        CampagneResume res2 = new CampagneResume() {
            public Long getId() { return 2L; }
            public String getNom() { return "Campagne 2"; }
            public BigDecimal getObjectifMontant() { return new BigDecimal("5000.00"); }
        };

        when(campagneRepository.findByDateDebutLessThanEqualAndDateFinGreaterThanEqual(now, now, CampagneResume.class))
                .thenReturn(List.of(res1, res2));

        List<CampagneResume> result = campagneService.getActiveCampagnes();

        assertEquals(2, result.size());
        assertEquals("Campagne 1", result.get(0).getNom());
    }
}