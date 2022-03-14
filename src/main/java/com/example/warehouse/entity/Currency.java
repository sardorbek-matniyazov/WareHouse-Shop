package com.example.warehouse.entity;

import com.example.warehouse.entity.templateEntity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Currency extends AbstractEntity {
    public Currency(Long id, String name, boolean active) {
        super(id, name, active);
    }

    public Currency() {
    }

    public Currency(String name, boolean active) {
        super(name, active);
    }
}
