package com.codeit_team01.sb07_hrbank_team01.department.entity;

import com.codeit_team01.sb07_hrbank_team01.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "departments")
public class Department extends BaseEntity {

    @Column(name = "name", nullable = false, unique = true, length = 50)
    private String name;

    @Column(name = "description", nullable = false, length = 200)
    private String description;

    @Column(name = "establish_date", nullable = false)
    private Instant establishDate;


    public void update(String newName, String newDescription, Instant newEstablishDate){
         if(newName != null && !newName.equals(this.name)){
             this.name = newName;
         }
         if(newDescription != null && !newDescription.equals(this.description)){
             this.description = newDescription;
         }
         if(newEstablishDate != null && !newEstablishDate.equals(this.establishDate)){
             this.establishDate = newEstablishDate;
         }


    }
}
