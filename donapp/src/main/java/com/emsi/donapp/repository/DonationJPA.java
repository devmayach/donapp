package com.emsi.donapp.repository;

import com.emsi.donapp.model.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DonationJPA extends JpaRepository<Donation, Long> {
    List<Donation> findByCampagneId(Long campagneId);
}