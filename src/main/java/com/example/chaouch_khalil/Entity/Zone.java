package com.example.chaouch_khalil.Entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String ref;
    private float dimension;

    @OneToOne
    private Personnel personnel;

    @ManyToOne
    private Parking parking;

    @OneToMany()
    private List<Personnel>personnels;
}
