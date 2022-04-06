package com.example.warehouse.entity;

import com.example.warehouse.entity.templateEntity.AbstractEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

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

    @NotNull(message = "Phone number cannot be null")
    @Column(unique = true, nullable = false)
    private String phoneNumber;
}
