package com.oop.pdiservice.entity;

import com.oop.pdiservice.enums.ItemStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "pdi_checklist_items")
public class PdiChecklistItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;

    private String description;

    @Enumerated(EnumType.STRING)
    private ItemStatus status;

    private String note;

    private String photoUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "checklist_id")
    private PdiChecklist checklist;

    public PdiChecklistItem() {
    }

    public PdiChecklistItem(String itemName, String description, ItemStatus status, String note, String photoUrl) {
        this.itemName = itemName;
        this.description = description;
        this.status = status;
        this.note = note;
        this.photoUrl = photoUrl;
    }

    @PrePersist
    public void onCreate() {
        if (this.status == null) {
            this.status = ItemStatus.NOT_CHECKED;
        }
    }

    public Long getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }

    public String getDescription() {
        return description;
    }

    public ItemStatus getStatus() {
        return status;
    }

    public String getNote() {
        return note;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public PdiChecklist getChecklist() {
        return checklist;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(ItemStatus status) {
        this.status = status;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public void setChecklist(PdiChecklist checklist) {
        this.checklist = checklist;
    }
}