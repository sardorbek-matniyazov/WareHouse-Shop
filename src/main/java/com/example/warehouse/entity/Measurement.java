package com.example.warehouse.entity;

import com.example.warehouse.entity.templateEntity.AbstractEntity;
import lombok.*;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Measurement extends AbstractEntity {
}
