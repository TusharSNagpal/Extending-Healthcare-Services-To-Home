package com.example.had.controllers;

import com.example.had.entities.FieldWorkerInHospital;
import com.example.had.payloads.ApiResponse;
import com.example.had.payloads.DoctorDto;
import com.example.had.payloads.FieldWorkerInHospitalDto;
import com.example.had.services.DoctorService;
import com.example.had.services.FieldWorkerInHospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fieldWorkerInHospital")
public class FieldWorkerInHospitalController {
    @Autowired
    FieldWorkerInHospitalService fieldWorkerInHospitalService;

    @PutMapping("/{fwInHospId}")
    public ResponseEntity<FieldWorkerInHospitalDto> updateFieldWorkerInHospital(@RequestBody FieldWorkerInHospitalDto fieldWorkerInHospitalDto, @PathVariable Integer fwInHospId) {
        FieldWorkerInHospitalDto updatedFieldWorkerInHospital = this.fieldWorkerInHospitalService.updateFieldWorkerInHospital(fieldWorkerInHospitalDto,fwInHospId);
        return ResponseEntity.ok(updatedFieldWorkerInHospital );
    }

    @GetMapping("/fwId/{fwInHospId}")
    public ResponseEntity<FieldWorkerInHospitalDto>getFieldWorkerInHospitalById(@PathVariable Integer fwInHospId) {
        return ResponseEntity.ok(this.fieldWorkerInHospitalService.geFieldWorkerInHospitalById(fwInHospId));
    }

    @PostMapping("/fwInHosp/{fwInHosp}/hospital/{hospitalId}")
    //public registerFieldWorker(@PathVariable Integer fwInHosp,@PathVariable Integer hospitalId) ;
    public ResponseEntity<ApiResponse>  registerFieldWorker(@PathVariable Integer fwInHosp, @PathVariable Integer hospitalId){
        this.fieldWorkerInHospitalService.registerFieldWorker(fwInHosp,hospitalId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Field Worker registered successfully",true), HttpStatus.OK);
    }

    @GetMapping("/hospital/{hospitalId}")
    public ResponseEntity<List<FieldWorkerInHospitalDto>> getFieldWorker(@PathVariable Integer hospitalId) {
        return ResponseEntity.ok(this.fieldWorkerInHospitalService.getFieldWorker(hospitalId));
    }

    @GetMapping("/phoneNo/{fwInHospId}")
    public ResponseEntity<String> getPhoneNo(@PathVariable Integer fwInHospId) {
        return ResponseEntity.ok(this.fieldWorkerInHospitalService.getPhoneNo(fwInHospId));
    }

}
