package com.example.gabriel.cursos_java.modules.course.dto;

import com.example.gabriel.cursos_java.modules.course.enitites.CoursesEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PutCoursesDTO {
    private UUID id;
    private String name;
    private String category;
    private Boolean active;
}
