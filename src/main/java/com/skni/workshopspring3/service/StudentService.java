package com.skni.workshopspring3.service;

import com.skni.workshopspring3.repo.StudentRepository;
import com.skni.workshopspring3.repo.entity.Course;
import com.skni.workshopspring3.repo.entity.CourseTypeEnum;
import com.skni.workshopspring3.repo.entity.GenderEnum;
import com.skni.workshopspring3.repo.entity.Student;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public boolean deleteStudentById(Long id){
        Optional<Student> student = studentRepository.findById(id);
        if(student.isPresent()){
            studentRepository.delete(student.get());
            return true;
        }
        return false;
    }
//
    public Student addStudent(String name, String lastname, LocalDate birthdate, GenderEnum gender, Course course){
        //Person person = new Person(name, lastname, birthdate, gender, address);

        Student student = Student.builder()
                .name(name)
                .lastname(lastname)
                .birthdate(birthdate)
                .gender(gender)
                .course(course)
                .build();

        return studentRepository.save(student);
    }

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public List<Student> findAllByLastName(String LastName){
        return studentRepository.findAllByLastName(LastName);
    }

    public List<Student> getStudentByGenderAndByCourseType(GenderEnum gender, CourseTypeEnum stopien){
        String sgender = gender.toString();
        String sstopien = stopien.toString();
        return studentRepository.getStudentByGenderAndByCourseType(sgender, sstopien);
    }

    public Optional<Student> getPersonById(Long id){
        return studentRepository.findById(id);
    }

}
