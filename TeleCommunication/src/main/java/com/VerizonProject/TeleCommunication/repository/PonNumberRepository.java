package com.VerizonProject.TeleCommunication.repository;

import com.VerizonProject.TeleCommunication.entity.PonStatus;
import com.VerizonProject.TeleCommunication.entity.PonNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface PonNumberRepository extends JpaRepository<PonNumber,Long> {
    Optional<PonNumber> findByponId(String ponId);
    List<PonNumber> findByStatus(PonStatus status);
}
