package com.emsi.donapp.controller;

import com.emsi.donapp.projection.CampagneResume;
import com.emsi.donapp.service.CampagneService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/campagnes")
public class CampagneController {
    private final CampagneService campagneService;

    public CampagneController(CampagneService campagneService) {
        this.campagneService = campagneService;
    }

    @GetMapping("/actives")
    public List<CampagneResume> getActiveCampagnes() {
        return campagneService.getActiveCampagnes();
    }
}