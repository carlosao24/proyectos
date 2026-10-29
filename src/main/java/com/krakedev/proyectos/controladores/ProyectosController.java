package com.krakedev.proyectos.controladores;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krakedev.proyectos.entidades.Proyecto;
import com.krakedev.proyectos.servicios.ServiceProyecto;

@RestController
@RequestMapping("/api/proyectos")
public class ProyectosController {
	
	private final ServiceProyecto serviceProyecto;
	
	public ProyectosController(ServiceProyecto serviceProyecto) {
		this.serviceProyecto = serviceProyecto;
	}
	
	@PostMapping
	public ResponseEntity<?> crear(@RequestBody Proyecto proyecto){
		try {
			Proyecto p = serviceProyecto.crear(proyecto);
			return ResponseEntity.status(HttpStatus.CREATED).body(p);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el proyecto");
		}
	}
	
	@GetMapping
	public ResponseEntity<?> listar(){
		try {
			List<Proyecto> proyectos = serviceProyecto.listar();
			return ResponseEntity.ok(proyectos);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al listar los proyectos");
		}
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable int id){
		try {
			Proyecto p = serviceProyecto.buscarPorId(id);
			if(p == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El proyecto con el "+id+" no fue encontrado");
			}
			return ResponseEntity.ok(p);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al listar el proyecto");
		}
		
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> actualizar(@PathVariable int id, @RequestBody Proyecto pnuevo){
		try {
			Proyecto pa = serviceProyecto.actualizar(id, pnuevo);
			if(pa == null ) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El proyecto con el "+id+" no fue encontrado");
			}
			
			return ResponseEntity.ok(pa);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el proyecto");
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable int id){
		try {
			boolean eliminado = serviceProyecto.eliminar(id);
			if(!eliminado) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El proyecto con el "+id+" no fue encontrado");
			}
			return ResponseEntity.ok("Proyecto eliminado");
			
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar");
		}
		
	}
	
	
}
