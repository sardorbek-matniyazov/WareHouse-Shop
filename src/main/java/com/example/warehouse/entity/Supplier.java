package com.example.warehouse.entity;

import com.example.warehouse.entity.templateEntity.AbstractEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Supplier extends AbstractEntity {

    public Supplier(Long id, String name, boolean active, String phoneNumber) {
        super(id, name, active);
        this.phoneNumber = phoneNumber;
    }

    public Supplier(String name, boolean active, String phoneNumber) {
        super(name, active);
        this.phoneNumber = phoneNumber;
    }

    @Column(unique = true, nullable = false)
    private String phoneNumber;
}
