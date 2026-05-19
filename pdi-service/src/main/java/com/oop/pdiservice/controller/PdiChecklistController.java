package com.oop.pdiservice.controller;

import com.oop.pdiservice.dto.AddChecklistItemRequest;
import com.oop.pdiservice.dto.CreatePdiChecklistRequest;
import com.oop.pdiservice.dto.PdiChecklistResponse;
import com.oop.pdiservice.dto.UpdateChecklistItemRequest;
import com.oop.pdiservice.service.PdiChecklistService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pdi-checklists")
public class PdiChecklistController {

    private final PdiChecklistService pdiChecklistService;

    public PdiChecklistController(PdiChecklistService pdiChecklistService) {
        this.pdiChecklistService = pdiChecklistService;
    }

    @PostMapping
    public ResponseEntity<PdiChecklistResponse> createChecklist(
            @Valid @RequestBody CreatePdiChecklistRequest request
    ) {
        PdiChecklistResponse response = pdiChecklistService.createChecklist(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PdiChecklistResponse>> getAllChecklists() {
        return ResponseEntity.ok(pdiChecklistService.getAllChecklists());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PdiChecklistResponse> getChecklistById(@PathVariable Long id) {
        return ResponseEntity.ok(pdiChecklistService.getChecklistById(id));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<PdiChecklistResponse>> getChecklistsByCustomerId(@PathVariable Long customerId) {
        return ResponseEntity.ok(pdiChecklistService.getChecklistsByCustomerId(customerId));
    }

    @PostMapping("/{checklistId}/items")
    public ResponseEntity<PdiChecklistResponse> addItem(
            @PathVariable Long checklistId,
            @Valid @RequestBody AddChecklistItemRequest request
    ) {
        return ResponseEntity.ok(pdiChecklistService.addItem(checklistId, request));
    }

    @PutMapping("/{checklistId}/items/{itemId}")
    public ResponseEntity<PdiChecklistResponse> updateItem(
            @PathVariable Long checklistId,
            @PathVariable Long itemId,
            @RequestBody UpdateChecklistItemRequest request
    ) {
        return ResponseEntity.ok(pdiChecklistService.updateItem(checklistId, itemId, request));
    }

    @PutMapping("/{checklistId}/complete")
    public ResponseEntity<PdiChecklistResponse> completeChecklist(@PathVariable Long checklistId) {
        return ResponseEntity.ok(pdiChecklistService.completeChecklist(checklistId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChecklist(@PathVariable Long id) {
        pdiChecklistService.deleteChecklist(id);
        return ResponseEntity.noContent().build();
    }
}