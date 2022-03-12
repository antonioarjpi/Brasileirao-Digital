package com.devsimple.brasileiraodigital.repository;


import com.devsimple.brasileiraodigital.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
