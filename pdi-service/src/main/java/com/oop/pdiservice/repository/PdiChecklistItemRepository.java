package com.oop.pdiservice.repository;

import com.oop.pdiservice.entity.PdiChecklistItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PdiChecklistItemRepository extends JpaRepository<PdiChecklistItem, Long> {
}