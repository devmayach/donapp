package com.emsi.donapp.service;

import com.emsi.donapp.dto.DonDTO;
import com.emsi.donapp.model.Donation;

public interface DonationService {
    Donation saveDonation(DonDTO donDTO);
    DonDTO convertToDTO(Donation donation);
}