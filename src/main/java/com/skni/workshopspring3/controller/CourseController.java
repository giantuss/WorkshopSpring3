package com.skni.workshopspring3.controller;

import com.skni.workshopspring3.dto.CourseRequest;
import com.skni.workshopspring3.dto.CourseResponse;
import com.skni.workshopspring3.repo.CourseRepository;
import com.skni.workshopspring3.repo.entity.Course;
import com.skni.workshopspring3.service.CourseService;
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
public class CourseController {

    private final CourseRepository courseRepository;
    private final CourseService courseService;
    private final ModelMapper modelMapper;

    @GetMapping("/course")
    public List<CourseResponse> getCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/course        /{id}")
    public ResponseEntity<Course> getCourse(@PathVariable Long id) {
        Optional<Course> result = courseRepository.findById(id);

        if(result.isPresent())
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping("/course")
    public void addCourse(@Valid @RequestBody CourseRequest course) {
        Course newCourse = modelMapper.map(course, Course.class);
        courseRepository.save(newCourse);
    }

    @DeleteMapping("/course/{id}")
    public ResponseEntity<?> addCourse(@PathVariable Long id) {
        if(courseRepository.findById(id).isPresent()){
            courseRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/course/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable Long id, @RequestBody Course course) {
        if(courseService.updateCourse(id, course)) {
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
