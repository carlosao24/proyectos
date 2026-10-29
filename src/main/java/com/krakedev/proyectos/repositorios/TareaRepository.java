package com.krakedev.proyectos.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.krakedev.proyectos.entidades.Tarea;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Integer>{

}
