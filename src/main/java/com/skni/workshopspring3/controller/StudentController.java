package com.skni.workshopspring3.controller;

import com.skni.workshopspring3.dto.StudentRequest;
import com.skni.workshopspring3.dto.StudentResponse;
import com.skni.workshopspring3.repo.StudentRepository;
import com.skni.workshopspring3.repo.entity.Student;
import com.skni.workshopspring3.service.StudentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class StudentController {

    private final StudentRepository studentRepository;
    private final StudentService studentService;
    private final ModelMapper modelMapper;

    @GetMapping("/student")
    public List<StudentResponse> getStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/student        /{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        Optional<Student> result = studentRepository.findById(id);

        if(result.isPresent())
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping("/student")
    public void addStudent(@Valid @RequestBody StudentRequest student) {
        Student newStudent = modelMapper.map(student, Student.class);
        studentRepository.save(newStudent);
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<?> addStudent(@PathVariable Long id) {
        if(studentRepository.findById(id).isPresent()){
            studentRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/student/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        if(studentService.updateStudent(id, student)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
