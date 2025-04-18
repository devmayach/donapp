package com.emsi.donapp.controller;

import com.emsi.donapp.dto.DonDTO;
import com.emsi.donapp.service.DonationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;

@RestController
@RequestMapping("/api/campagnes/{id}/dons")
public class DonationController {
    private final DonationService donationService;

    public DonationController(DonationService donationService) {
        this.donationService = donationService;
    }

    @PostMapping
    public ResponseEntity<DonDTO> createDonation(
            @PathVariable Long id,
            @Valid @RequestBody DonDTO donDTO) {
        donDTO.setCampagneId(id);
        Donation donation = donationService.saveDonation(donDTO);
        DonDTO responseDTO = donationService.convertToDTO(donation);
        return ResponseEntity
                .created(URI.create("/api/campagnes/" + id + "/dons/" + donation.getId()))
                .body(responseDTO);
    }
}