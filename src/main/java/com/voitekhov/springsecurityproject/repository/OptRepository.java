package com.voitekhov.springsecurityproject.repository;

import com.voitekhov.springsecurityproject.model.Opt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OptRepository extends JpaRepository<Opt, Integer> {

    @Query("SELECT o FROM Opt o where o.user.id=:Uid")
    Optional<Opt> getOptByUsername(@Param("Uid") Integer Uid);

}
