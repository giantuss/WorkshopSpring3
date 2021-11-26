package com.skni.workshopspring3.repo;

import com.skni.workshopspring3.repo.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
