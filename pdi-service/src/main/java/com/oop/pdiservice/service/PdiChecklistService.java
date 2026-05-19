package com.oop.pdiservice.service;

import com.oop.pdiservice.dto.*;
import com.oop.pdiservice.entity.PdiChecklist;
import com.oop.pdiservice.entity.PdiChecklistItem;
import com.oop.pdiservice.enums.ChecklistStatus;
import com.oop.pdiservice.enums.ItemStatus;
import com.oop.pdiservice.exception.ResourceNotFoundException;
import com.oop.pdiservice.repository.PdiChecklistItemRepository;
import com.oop.pdiservice.repository.PdiChecklistRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PdiChecklistService {

    private final PdiChecklistRepository checklistRepository;
    private final PdiChecklistItemRepository itemRepository;

    public PdiChecklistService(PdiChecklistRepository checklistRepository,
                               PdiChecklistItemRepository itemRepository) {
        this.checklistRepository = checklistRepository;
        this.itemRepository = itemRepository;
    }

    public PdiChecklistResponse createChecklist(CreatePdiChecklistRequest request) {
        PdiChecklist checklist = new PdiChecklist(
                request.getCustomerId(),
                request.getVehiclePlate(),
                request.getVehicleModel(),
                request.getInspectionType()
        );

        PdiChecklist savedChecklist = checklistRepository.save(checklist);
        return mapToResponse(savedChecklist);
    }

    public List<PdiChecklistResponse> getAllChecklists() {
        return checklistRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public PdiChecklistResponse getChecklistById(Long id) {
        PdiChecklist checklist = findChecklistById(id);
        return mapToResponse(checklist);
    }

    public List<PdiChecklistResponse> getChecklistsByCustomerId(Long customerId) {
        return checklistRepository.findByCustomerId(customerId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public PdiChecklistResponse addItem(Long checklistId, AddChecklistItemRequest request) {
        PdiChecklist checklist = findChecklistById(checklistId);

        PdiChecklistItem item = new PdiChecklistItem(
                request.getItemName(),
                request.getDescription(),
                request.getStatus() == null ? ItemStatus.NOT_CHECKED : request.getStatus(),
                request.getNote(),
                request.getPhotoUrl()
        );

        item.setChecklist(checklist);
        checklist.getItems().add(item);
        checklist.setStatus(ChecklistStatus.IN_PROGRESS);

        PdiChecklist updatedChecklist = checklistRepository.save(checklist);
        return mapToResponse(updatedChecklist);
    }

    public PdiChecklistResponse updateItem(Long checklistId, Long itemId, UpdateChecklistItemRequest request) {
        PdiChecklist checklist = findChecklistById(checklistId);

        PdiChecklistItem item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Checklist item not found with id: " + itemId));

        if (!item.getChecklist().getId().equals(checklist.getId())) {
            throw new ResourceNotFoundException("Checklist item does not belong to checklist id: " + checklistId);
        }

        item.setStatus(request.getStatus());
        item.setNote(request.getNote());
        item.setPhotoUrl(request.getPhotoUrl());

        itemRepository.save(item);

        PdiChecklist updatedChecklist = findChecklistById(checklistId);
        return mapToResponse(updatedChecklist);
    }

    public PdiChecklistResponse completeChecklist(Long checklistId) {
        PdiChecklist checklist = findChecklistById(checklistId);

        boolean hasFailedItem = checklist.getItems()
                .stream()
                .anyMatch(item -> item.getStatus() == ItemStatus.FAIL);

        checklist.setStatus(hasFailedItem ? ChecklistStatus.FAILED : ChecklistStatus.COMPLETED);
        checklist.setCompletedAt(LocalDateTime.now());

        PdiChecklist updatedChecklist = checklistRepository.save(checklist);
        return mapToResponse(updatedChecklist);
    }

    public void deleteChecklist(Long id) {
        PdiChecklist checklist = findChecklistById(id);
        checklistRepository.delete(checklist);
    }

    private PdiChecklist findChecklistById(Long id) {
        return checklistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PDI checklist not found with id: " + id));
    }

    private PdiChecklistResponse mapToResponse(PdiChecklist checklist) {
        List<PdiChecklistItemResponse> itemResponses = checklist.getItems()
                .stream()
                .map(item -> new PdiChecklistItemResponse(
                        item.getId(),
                        item.getItemName(),
                        item.getDescription(),
                        item.getStatus(),
                        item.getNote(),
                        item.getPhotoUrl()
                ))
                .toList();

        return new PdiChecklistResponse(
                checklist.getId(),
                checklist.getCustomerId(),
                checklist.getVehiclePlate(),
                checklist.getVehicleModel(),
                checklist.getInspectionType(),
                checklist.getStatus(),
                checklist.getCreatedAt(),
                checklist.getCompletedAt(),
                itemResponses
        );
    }
}