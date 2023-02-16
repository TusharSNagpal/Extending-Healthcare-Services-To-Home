package com.example.had.services;

import com.example.had.payloads.VisitDto;

import java.util.List;

public interface VisitService {
    void createVisit(VisitDto visitDto);
    List<VisitDto> activeVisits(int hospitalId);
}
