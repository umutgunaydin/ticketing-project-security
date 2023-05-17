package com.company.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "roles")
public class Role extends BaseEntity{

    private String description;

}
