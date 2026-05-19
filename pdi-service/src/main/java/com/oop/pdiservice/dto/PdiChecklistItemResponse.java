package com.oop.pdiservice.dto;

import com.oop.pdiservice.enums.ItemStatus;

public class PdiChecklistItemResponse {

    private Long id;
    private String itemName;
    private String description;
    private ItemStatus status;
    private String note;
    private String photoUrl;

    public PdiChecklistItemResponse(Long id, String itemName, String description,
                                    ItemStatus status, String note, String photoUrl) {
        this.id = id;
        this.itemName = itemName;
        this.description = description;
        this.status = status;
        this.note = note;
        this.photoUrl = photoUrl;
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
}