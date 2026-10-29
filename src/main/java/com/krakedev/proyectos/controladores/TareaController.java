package com.krakedev.proyectos.controladores;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krakedev.proyectos.entidades.Tarea;
import com.krakedev.proyectos.servicios.ServiceTarea;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {
	
	private final ServiceTarea serviceTarea;
	
	public TareaController(ServiceTarea serviceTarea) {
		this.serviceTarea = serviceTarea;
	}
	
	@PostMapping
	public ResponseEntity<?> crear(@RequestBody Tarea tarea){
		try {
			Tarea t = serviceTarea.crear(tarea);
			return ResponseEntity.status(HttpStatus.CREATED).body(t);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la Tarea");
		}
	}
	
	@GetMapping
	public ResponseEntity<?> listar(){
		try {
			List<Tarea> tareas = serviceTarea.listar();
			return ResponseEntity.ok(tareas);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al listar las tareas");
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable int id){
		try {
			Tarea te = serviceTarea.buscarPorId(id);
			if(te == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarea no encontrada");
			}
			return ResponseEntity.ok(te);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al listar la tarea");
		}
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<?> actualizar(@PathVariable int id, @RequestBody Tarea nuevaTa){
		try {
			Tarea nuevaTarea = serviceTarea.actualizar(id, nuevaTa);
			if(nuevaTarea == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarea no encontrada");
			}
			return ResponseEntity.ok(nuevaTarea);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar la tarea");
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable int id){
		try {
			boolean r = serviceTarea.eliminar(id);
			if(!r) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarea no encontrada");
			}
			return ResponseEntity.ok("Tarea eliminada");
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la tarea");
		}
	}
	
}
