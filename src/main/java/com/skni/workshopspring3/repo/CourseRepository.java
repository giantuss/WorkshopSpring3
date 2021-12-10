package com.skni.workshopspring3.repo;

import java.util.List;
import java.util.Optional;

import com.skni.workshopspring3.repo.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Override
    List<Course> findAll();

    @Override
    Optional<Course> findById(Long id);

}

