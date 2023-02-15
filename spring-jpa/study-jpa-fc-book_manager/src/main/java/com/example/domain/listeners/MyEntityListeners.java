package com.example.domain.listeners;


import javax.persistence.*;
import java.time.LocalDateTime;

public class MyEntityListeners {

    @PrePersist
    public void prePersist(Object o) {
        dateTimeInsertAndUpdate(o);
    }

    @PreUpdate
    public void preUpdate(Object o) {
        dateTimeInsertAndUpdate(o);
    }

    private void dateTimeInsertAndUpdate(Object o) {
        if (o instanceof Auditable) {
            ((Auditable) o).setCreatedAt(LocalDateTime.now());
            ((Auditable) o).setUpdatedAt(LocalDateTime.now());
        }
    }
}
