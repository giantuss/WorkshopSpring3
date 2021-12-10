package com.skni.workshopspring3.service;

import com.skni.workshopspring3.dto.StudentRequest;
import com.skni.workshopspring3.dto.StudentResponse;
import com.skni.workshopspring3.repo.CourseRepository;
import com.skni.workshopspring3.repo.StudentRepository;
import com.skni.workshopspring3.repo.entity.Course;
import com.skni.workshopspring3.repo.entity.CourseTypeEnum;
import com.skni.workshopspring3.repo.entity.GenderEnum;
import com.skni.workshopspring3.repo.entity.Student;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;

    public boolean deleteStudentById(Long id){
        if(studentRepository.findById(id).isPresent()){
            studentRepository.delete(studentRepository.findById(id).get());
            return true;
        }
        return false;
    }

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

    public List<Student> findAllByLastName(String LastName){
        return studentRepository.findAllByLastName(LastName);
    }

    public List<Student> getStudentByGenderAndByCourseType(GenderEnum gender, CourseTypeEnum stopien){
        String sgender = gender.toString();
        String sstopien = stopien.toString();
        return studentRepository.getStudentByGenderAndByCourseType(sgender, sstopien);
    }



    public ResponseEntity<?> addStudentWithStudentRequest(StudentRequest studentRequest){
        var student = Student.builder()
                .name(studentRequest.getName())
                .birthdate(studentRequest.getBirthdate())
                .lastname(studentRequest.getLastname())
                .gender(studentRequest.getGender())
                .build();

        studentRepository.save(student);

        if(studentRepository.findById(student.getId()).isPresent())
            return new ResponseEntity<>(HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.CONFLICT);

    }

    public List<StudentResponse> getAllStudents() {
        List<Student> students = studentRepository.findAll();

        List<StudentResponse> result = new ArrayList<>();
        for(Student s : students){
            StudentResponse studentResponse = modelMapper.map(s, StudentResponse.class);
            result.add(studentResponse);
        }
        return result;
    }

    public boolean updateStudent(long id, Student updatedStudent) {
        Optional<Student> currentStudent = studentRepository.findById(id);
        if(currentStudent.isPresent()) {
            Student newStudent = currentStudent.get();
            if(updatedStudent.getName() != null) {
                newStudent.setName(updatedStudent.getName());
            }
            if(updatedStudent.getLastname() != null) {
                newStudent.getLastname(updatedStudent.getLastname());
            }
            if(updatedStudent.getBirthdate() != null) {
                newStudent.setBirthdate(updatedStudent.getBirthdate());
            }
            if(updatedStudent.getGender() != null) {
                newStudent.setGender(updatedStudent.getGender());
            }
            newStudent.setId(id);
            studentRepository.save(newStudent);
            return true;
        }
        return false;
    }

}
