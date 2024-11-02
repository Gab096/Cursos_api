package com.example.gabriel.cursos_java.modules.course.services;

import com.example.gabriel.cursos_java.exceptions.UserFoundException;
import com.example.gabriel.cursos_java.modules.course.dto.GetCoursesDTO;
import com.example.gabriel.cursos_java.modules.course.enitites.CoursesEntity;
import com.example.gabriel.cursos_java.modules.course.repositories.CoursesRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CourseServices {

    @Autowired
    private CoursesRepository coursesRepository;
    public CoursesEntity execute(@Valid @RequestBody CoursesEntity coursesEntity){
            return coursesRepository.save(coursesEntity);
    }

    public Optional<List<CoursesEntity>> get(){
        return Optional.of(coursesRepository.findAll());
    }

    public CoursesEntity update(@Valid @RequestBody CoursesEntity coursesEntity) {
        Optional<CoursesEntity> existingCourse = coursesRepository.findById(coursesEntity.getId());
        if (existingCourse.isPresent()) {
            CoursesEntity courseToUpdate = existingCourse.get();
            courseToUpdate.setName(coursesEntity.getName());
            courseToUpdate.setActive(coursesEntity.getActive());
            courseToUpdate.setCategory(coursesEntity.getCategory());
            return coursesRepository.save(courseToUpdate);
        } else {
            throw new UserFoundException();
        }
    }

    public String delete(UUID id){
        try {
            coursesRepository.deleteById(id);
            return "Usuário Deletado";
        }catch (Exception e){
            e.printStackTrace();
            return "Error ao deletar";
        }

    }
    public String patch(UUID id){
        Optional<CoursesEntity> existingCourse = coursesRepository.findById(id);
        if (existingCourse.isPresent()) {
            CoursesEntity courseToUpdate = existingCourse.get();
            courseToUpdate.setActive(!courseToUpdate.getActive());
            coursesRepository.save(courseToUpdate);
            return "Curso atualizado com sucesso!";
        } else {
            return  "Curso não encontrado para o ID fornecido.";
        }

    }
}
