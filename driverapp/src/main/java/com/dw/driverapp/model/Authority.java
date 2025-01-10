package com.dw.driverapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "권한")
public class Authority {
    @Id
    @Column(name = "authority_name", length = 50)
    private String authorityName;
}