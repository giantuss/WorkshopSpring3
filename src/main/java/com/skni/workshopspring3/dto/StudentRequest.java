package com.skni.workshopspring3.dto;

import com.skni.workshopspring3.repo.entity.Course;
import com.skni.workshopspring3.repo.entity.GenderEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class StudentRequest {
    @NotBlank(message = "name is mandatory")
    @Size(min = 3, max = 30)
    private String name;

    @NotBlank(message = "lastname is mandatory")
    @Size(min = 3, max = 30)
    private String lastname;

    private LocalDate birthdate;
    private GenderEnum gender;
    private Long course_id;

}
