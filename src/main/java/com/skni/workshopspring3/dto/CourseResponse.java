package com.skni.workshopspring3.dto;

import com.skni.workshopspring3.repo.entity.CourseTypeEnum;
import com.skni.workshopspring3.repo.entity.Student;

import javax.persistence.*;
import java.util.List;

public class CourseResponse {
    private String nazwa;
    private String nazwauczelni;
    private CourseTypeEnum stopien;
    private int czastrwania;
    private List<Student> students;
}
