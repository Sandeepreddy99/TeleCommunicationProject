package com.VerizonProject.TeleCommunication.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="pon_numbers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PonNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true,nullable = false)
    private String ponId;
    @Enumerated(EnumType.STRING)
    private PonStatus status;
    @ManyToOne
    private Customer customer;
}
