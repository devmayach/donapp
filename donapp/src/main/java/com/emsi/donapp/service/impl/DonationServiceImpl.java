package com.emsi.donapp.service.impl;

import com.emsi.donapp.dto.DonDTO;
import com.emsi.donapp.model.Campagne;
import com.emsi.donapp.model.Donation;
import com.emsi.donapp.repository.CampagneJPA;
import com.emsi.donapp.repository.DonationJPA;
import com.emsi.donapp.service.DonationService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class DonationServiceImpl implements DonationService {
    private final DonationJPA donationRepository;
    private final CampagneJPA campagneRepository;

    public DonationServiceImpl(DonationJPA donationRepository, CampagneJPA campagneRepository) {
        this.donationRepository = donationRepository;
        this.campagneRepository = campagneRepository;
    }

    @Override
    public Donation saveDonation(DonDTO donDTO) {
        Campagne campagne = campagneRepository.findById(donDTO.getCampagneId())
                .orElseThrow(() -> new IllegalArgumentException("Campagne non trouvée"));

        Donation donation = new Donation();
        donation.setCampagne(campagne);
        donation.setNomDonateur(donDTO.getNomDonateur());
        donation.setMontant(donDTO.getMontant());
        donation.setDate(LocalDateTime.now());

        return donationRepository.save(donation);
    }

    @Override
    public DonDTO convertToDTO(Donation donation) {
        DonDTO donDTO = new DonDTO();
        donDTO.setId(donation.getId());
        donDTO.setNomDonateur(donation.getNomDonateur());
        donDTO.setMontant(donation.getMontant());
        donDTO.setDate(donation.getDate());
        donDTO.setCampagneId(donation.getCampagne().getId());
        return donDTO;
    }
}