package com.example.gabriel.cursos_java.modules.course.repositories;
import com.example.gabriel.cursos_java.modules.course.enitites.CoursesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;



public interface CoursesRepository extends JpaRepository<CoursesEntity,UUID > {
    Optional<CoursesEntity> findByNameOrCategory(String name , String Category);

}

