package com.oop.pdiservice.repository;

import com.oop.pdiservice.entity.PdiChecklist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PdiChecklistRepository extends JpaRepository<PdiChecklist, Long> {

    List<PdiChecklist> findByCustomerId(Long customerId);
}