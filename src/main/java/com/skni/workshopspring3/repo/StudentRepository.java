package com.skni.workshopspring3.repo;

import java.util.List;
import java.util.Optional;

import com.skni.workshopspring3.repo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Override
    List<Student> findAll();

    @Override
    Optional<Student> findById(Long id);


    @Query(
            value = "SELECT distinct s.* FROM student s WHERE s.lastname = ?1 ",
            nativeQuery = true)
    List<Student> findAllByLastName(String lastname);

    @Query(
            value = "SELECT distinct s.* FROM student s JOIN course c ON s.course_id = c.id WHERE s.gender = ?1 " +
                    "AND c.stopien = ?2",
            nativeQuery = true)
    List<Student> getStudentByGenderAndByCourseType(String gender, String stopien);
}
