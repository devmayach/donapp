package com.emsi.donapp.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DonDTO {
    private Long id;

    @NotBlank(message = "Le nom du donateur est obligatoire")
    private String nomDonateur;

    @NotNull(message = "Le montant est obligatoire")
    @DecimalMin(value = "0.01", message = "Le montant doit être supérieur à 0")
    private BigDecimal montant;

    private LocalDateTime date;

    private Long campagneId;

}
