package com.example.chaouch_khalil.Repository;

import com.example.chaouch_khalil.Entity.Parking;
import com.example.chaouch_khalil.Entity.Personnel;
import com.example.chaouch_khalil.Entity.Poste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonnelRepository extends JpaRepository<Personnel , Long> {

    List<Personnel> findByParking(Parking parking);

    @Query(" select count(c) from Personnel c INNER JOIN Zone z on  z.personnel=c.id where c.poste=:garde_jour")
    int getPersonnelByPoste(@Param("post") String adresse);
}
