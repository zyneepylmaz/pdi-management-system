package com.oop.pdiservice.dto;

import com.oop.pdiservice.enums.ItemStatus;

public class UpdateChecklistItemRequest {

    private ItemStatus status;

    private String note;

    private String photoUrl;

    public ItemStatus getStatus() {
        return status;
    }

    public String getNote() {
        return note;
    }

    public String getPhotoUrl() {
        return photoUrl;
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