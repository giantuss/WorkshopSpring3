package com.skni.workshopspring3.dto;

import com.skni.workshopspring3.repo.entity.Course;
import com.skni.workshopspring3.repo.entity.GenderEnum;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
public class StudentResponse {
    private String name;
    private String lastname;
    private LocalDate birthdate;
    private GenderEnum gender;
}