package com.oop.pdiservice.dto;

import com.oop.pdiservice.enums.ItemStatus;
import jakarta.validation.constraints.NotBlank;

public class AddChecklistItemRequest {

    @NotBlank(message = "Item name is required")
    private String itemName;

    private String description;

    private ItemStatus status;

    private String note;

    private String photoUrl;

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
}