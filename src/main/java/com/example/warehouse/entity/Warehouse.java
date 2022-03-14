package com.example.warehouse.entity;

import com.example.warehouse.entity.templateEntity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@Entity
public class Warehouse extends AbstractEntity {
    public Warehouse(Long id, String name, boolean active) {
        super(id, name, active);
    }
    public Warehouse(String name, boolean active) {
        super(name, active);
    }
}
