package com.brunofelixdev.springdatajpa.dao;

import com.brunofelixdev.springdatajpa.models.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITefefoneRepository extends JpaRepository<Telefone, Long> {


}
