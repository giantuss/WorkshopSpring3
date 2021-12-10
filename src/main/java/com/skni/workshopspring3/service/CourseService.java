package com.skni.workshopspring3.service;

import com.skni.workshopspring3.dto.CourseRequest;
import com.skni.workshopspring3.dto.CourseResponse;
import com.skni.workshopspring3.repo.CourseRepository;
import com.skni.workshopspring3.repo.entity.Course;
import com.skni.workshopspring3.repo.entity.CourseTypeEnum;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;

    public boolean deleteCourseById(Long id){
        Optional<Course> course = courseRepository.findById(id);
        if(course.isPresent()){
            courseRepository.delete(course.get());
            return true;
        }
        return false;
    }
    //Course course = courseService.addCourse("Informatyka", 3, "SGH", CourseTypeEnum.INZYNIER);
    public Course addCourse(String nazwa, int czastrwania, String nazwauczelni, CourseTypeEnum stopien){
        Course course = Course.builder()
                .nazwa(nazwa)
                .czastrwania(czastrwania)
                .nazwauczelni(nazwauczelni)
                .stopien(stopien)
                .build();

        return courseRepository.save(course);
    }

    public ResponseEntity<?> addCourseWithRequest(CourseRequest courseRequest) {
        var course = Course.builder()
                .stopien(courseRequest.getStopien())
                .nazwa(courseRequest.getNazwa())
                .czastrwania(courseRequest.getCzastrwania())
                .nazwauczelni(courseRequest.getNazwauczelni())
                .build();

        courseRepository.save(course);
        if(courseRepository.findById(course.getId()).isPresent())
            return new ResponseEntity<>(HttpStatus.OK);

        return  new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    public List<CourseResponse> getAllCourses() {
        List<Course> courses = courseRepository.findAll();

        List<CourseResponse> result = new ArrayList<>();
        for(Course c : courses){
            CourseResponse courseResponse = modelMapper.map(c, CourseResponse.class);
            result.add(courseResponse);
        }

        return result;
    }



    public boolean updateCourse(long id, Course updatedCourse) {
        Optional<Course> currentCourse = courseRepository.findById(id);
        if(currentCourse.isPresent()) {
            Course newCourse = currentCourse.get();
            if(updatedCourse.getNazwa() != null) {
                newCourse.setNazwa(updatedCourse.getNazwa());
            }
            if(updatedCourse.getNazwauczelni() != null) {
                newCourse.setNazwauczelni(updatedCourse.getNazwauczelni());
            }
            if(updatedCourse.getStopien() != null) {
                newCourse.setStopien(updatedCourse.getStopien());
            }
            newCourse.setId(id);
            courseRepository.save(newCourse);
            return true;
        }
        return false;
    }
}
