package com.skni.workshopspring3.service;

import com.skni.workshopspring3.repo.CourseRepository;
import com.skni.workshopspring3.repo.entity.Course;
import java.util.Optional;

import com.skni.workshopspring3.repo.entity.CourseTypeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.skni.workshopspring3.repo.CourseRepository;
//
@Slf4j
@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

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
}
