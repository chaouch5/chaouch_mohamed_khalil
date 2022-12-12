package com.example.chaouch_khalil.Controller;


import com.example.chaouch_khalil.Entity.Parking;
import com.example.chaouch_khalil.Entity.Personnel;
import com.example.chaouch_khalil.Entity.Zone;
import com.example.chaouch_khalil.Repository.ParkingRepsitory;
import com.example.chaouch_khalil.Repository.PersonnelRepository;
import com.example.chaouch_khalil.Repository.ZoneRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("rest")

public class ParkingRestController {

    @Autowired
    ParkingRepsitory parkingRepsitory;
    @Autowired
    PersonnelRepository personnelRepository;
    @Autowired
    ZoneRepository zoneRepository;

    @PostMapping("/add-Parkingzone")
    public Parking ajouter(@RequestBody Parking parking) {
        Zone zone = saveZone(parking);
        zone.setParking(parking);
        return parkingRepsitory.save(parking);
    }

    private Zone saveZone(Parking parking) {
        Set<Zone> zones = (Set<Zone>) parking.getZone();
        for (Zone zone : zones) {
            return zoneRepository.save(zone);
        }
        return null;
    }

    @PostMapping("/add-Personnel")
    public Personnel ajouter(@RequestBody Personnel personnel) {
        return personnelRepository.save(personnel);
    }

    @GetMapping("/affecterzoneGarde/{idZone}/{idGarde}")
    public void affecterzoneGarde(@PathVariable Long idZone, @PathVariable Long idGarde) {

        Zone zone = zoneRepository.findById(idZone).orElse(null);
        Personnel personnel = personnelRepository.findById(idGarde).orElse(null);

        //n'oubliez pas que c'est une relation unidirectionnel coté user
        personnel.setZones(zone);
        personnelRepository.save(personnel);


    }


    @GetMapping("/listePersonnel/{parking}")
    List<Personnel> listePersonnel(@PathVariable("parking") Parking parking) {
        return personnelRepository.findByParking(parking);
    }


    @GetMapping("/nbrgarde/{adresse}")
    public Integer nbrgarde(@PathVariable String adresse) {
        Integer nbr = 0;

        List<Personnel> personnels = personnelRepository.findAll();
        for (Personnel personnel : personnels) {
            if (personnel.getZones().getParking().getAdresse().equals(adresse)) {
                nbr++;
            }
        }

        return nbr;

    }
}

