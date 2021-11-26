package com.skni.workshopspring3.repo.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "course")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Course {
    //public Course addCourse(String nazwa, int czastrwania, String nazwauczelni, CourseTypeEnum stopien){
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "nazwa")
    private String nazwa;

    @Column(name = "czastrwania")
    private int czastrwania;

    @Column(name = "nazwauczelni")
    private String nazwauczelni;

    @Enumerated(EnumType.STRING)
    @Column(name = "stopien")
    private CourseTypeEnum stopien;
}
