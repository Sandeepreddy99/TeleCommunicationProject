package com.VerizonProject.TeleCommunication.controller;

import com.VerizonProject.TeleCommunication.entity.PonNumber;
import com.VerizonProject.TeleCommunication.service.PonNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pon-numbers")
public class PonNumberController {
    @Autowired
private PonNumberService ponNumberService;
    @GetMapping
    public ResponseEntity<List<PonNumber>> getAllPonNumbers(){
        return new ResponseEntity<List<PonNumber>>(ponNumberService.getAllPonNumbers(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PonNumber> getPonNumberById(@PathVariable Long id){
        return new ResponseEntity<PonNumber>(ponNumberService.getPonNumberById(id),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<PonNumber> provisionPonNumber(@RequestBody PonNumber ponNumber){
        return new ResponseEntity<PonNumber>(ponNumberService.provisionPonNumber(ponNumber),HttpStatus.CREATED);
    }
    @PutMapping("/{ponId}/assign")
    public ResponseEntity<PonNumber> assignPonNumber(@PathVariable String ponId) {
        return ResponseEntity.ok(ponNumberService.assignPonNumber(ponId));
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivatePonNumber(@PathVariable Long id) {
        ponNumberService.deactivatePonNumber(id);
        return ResponseEntity.noContent().build();
    }
}
