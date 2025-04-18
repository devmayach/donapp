package com.emsi.donapp.service;

import com.emsi.donapp.projection.CampagneResume;
import java.util.List;

public interface CampagneService {
    List<CampagneResume> getActiveCampagnes();
}