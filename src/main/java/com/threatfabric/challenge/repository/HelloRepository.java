package com.threatfabric.challenge.repository;

import com.threatfabric.challenge.model.Hello;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HelloRepository extends JpaRepository<Hello, Long> {

    List<Hello> findAll();

}
