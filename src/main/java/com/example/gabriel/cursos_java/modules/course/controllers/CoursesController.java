package com.example.gabriel.cursos_java.modules.course.controllers;

import com.example.gabriel.cursos_java.modules.course.dto.PutCoursesDTO;
import com.example.gabriel.cursos_java.modules.course.enitites.CoursesEntity;
import com.example.gabriel.cursos_java.modules.course.services.CourseServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/courses")
public class CoursesController {
    @Autowired
    private CourseServices courseServices;

    @GetMapping("/")
    public ResponseEntity<Object> get(){
        try {
            var result = this.courseServices.get();
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CoursesEntity coursesEntity) {
        try {
            var result = this.courseServices.execute(coursesEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    };
    @PutMapping("/")
    public ResponseEntity<Object> update(@Valid @RequestBody PutCoursesDTO putCoursesDTO ) {
        try {

            var UpdateEntity = CoursesEntity.builder()
                    .id(putCoursesDTO.getId())
                    .name(putCoursesDTO.getName())
                    .active(putCoursesDTO.getActive())
                    .category(putCoursesDTO.getCategory())
                    .build();
            var result = this.courseServices.update(UpdateEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    };

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id){
        try {
            var result = this.courseServices.delete(id);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/active")
    public ResponseEntity<Object> patchActive(@PathVariable UUID id){
        try {
            var result = this.courseServices.patch(id);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



}
