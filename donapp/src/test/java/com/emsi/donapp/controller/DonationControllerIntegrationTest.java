package com.emsi.donapp.controller;

import com.emsi.donapp.dto.DonDTO;
import com.emsi.donapp.model.Campagne;
import com.emsi.donapp.repository.CampagneJPA;
import com.emsi.donapp.repository.DonationJPA;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.math.BigDecimal;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class DonationControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CampagneJPA campagneRepository;

    @Autowired
    private DonationJPA donationRepository;

    @BeforeEach
    void setUp() {
        donationRepository.deleteAll();
        campagneRepository.deleteAll();

        Campagne campagne = new Campagne();
        campagne.setNom("Test Campagne");
        campagne.setObjectifMontant(new BigDecimal("1000.00"));
        campagne.setDateDebut(java.time.LocalDate.now().minusDays(1));
        campagne.setDateFin(java.time.LocalDate.now().plusDays(30));
        campagneRepository.save(campagne);
    }

    @Test
    void createDonation_ShouldReturnCreatedDonation() throws Exception {
        Long campagneId = campagneRepository.findAll().get(0).getId();

        String donJson = """
            {
                "nomDonateur": "Jean Dupont",
                "montant": 100.00
            }
            """;

        mockMvc.perform(MockMvcRequestBuilders.post("/api/campagnes/{id}/dons", campagneId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(donJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nomDonateur").value("Jean Dupont"))
                .andExpect(jsonPath("$.montant").value(100.00));
    }
}