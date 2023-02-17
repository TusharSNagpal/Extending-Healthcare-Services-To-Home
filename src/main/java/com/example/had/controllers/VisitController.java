package com.example.had.controllers;

import com.example.had.payloads.ApiResponse;
import com.example.had.payloads.VisitDto;
import com.example.had.services.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visits")
public class VisitController {
    @Autowired
    private VisitService visitService;

    // POST - create visit
    @PostMapping("/")
    public ResponseEntity<ApiResponse> createUser(@RequestBody VisitDto visitDto){
//        System.out.println(visitDto.);
        this.visitService.createVisit(visitDto);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Visit created successfully",true), HttpStatus.OK);
    }

    @GetMapping("/activeVisits/hospital/{hospitalId}")
    public ResponseEntity<List<VisitDto>> getActiveVisits(@PathVariable int hospitalId){
        return ResponseEntity.ok(this.visitService.activeVisits(hospitalId));
    }
}
