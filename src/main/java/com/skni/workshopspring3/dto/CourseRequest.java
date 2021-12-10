package com.skni.workshopspring3.dto;

import com.skni.workshopspring3.repo.entity.CourseTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@AllArgsConstructor
@Data
public class CourseRequest {
    private String nazwa;
    private String nazwauczelni;
    private CourseTypeEnum stopien;
    private int czastrwania;
}