package com.VerizonProject.TeleCommunication.service;

import com.VerizonProject.TeleCommunication.entity.PonNumber;
import com.VerizonProject.TeleCommunication.entity.PonStatus;
import com.VerizonProject.TeleCommunication.repository.PonNumberRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PonNumberService {
    @Autowired
    private PonNumberRepository ponNumberRepository;
    //Retrieve all pon numbers
    public List<PonNumber> getAllPonNumbers(){
        return ponNumberRepository.findAll();
    }
    // Retrieve a Pon number by Id
    public PonNumber getPonNumberById(Long id){
        return ponNumberRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Pon Number not found"));
    }
    // provision a new Pon
    @Transactional
    public PonNumber provisionPonNumber(PonNumber ponNumber){
        ponNumber.setStatus(PonStatus.AVAILABLE);
        return ponNumberRepository.save(ponNumber);
    }
    // Assign a pon number to a customer
    @Transactional
    public PonNumber assignPonNumber(String ponId){
        PonNumber ponNumber=ponNumberRepository.findByponId(ponId)
                .orElseThrow(()->new EntityNotFoundException("Pon Number not Found"));
        if(ponNumber.getStatus()!=PonStatus.AVAILABLE){
            throw new IllegalStateException("Pon number is not available for assignment");
        }
        ponNumber.setStatus(PonStatus.ASSIGNED);
        return ponNumberRepository.save(ponNumber);
    }
    //Deactivate a Pon Number
    @Transactional
    public void deactivatePonNumber(Long id){
        PonNumber ponNumber=getPonNumberById(id);
        ponNumber.setStatus(PonStatus.INACTIVE);
        ponNumberRepository.save(ponNumber);
    }
}
