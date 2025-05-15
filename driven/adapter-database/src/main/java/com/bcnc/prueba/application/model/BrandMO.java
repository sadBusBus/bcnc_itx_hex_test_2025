package com.bcnc.prueba.application.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "BRANDS")
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class BrandMO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String  name;

}
