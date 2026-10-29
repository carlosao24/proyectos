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

import com.krakedev.proyectos.entidades.Empleado;
import com.krakedev.proyectos.servicios.ServiceEmpleado;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

	private final ServiceEmpleado serviceEmpleado;
	
	public EmpleadoController(ServiceEmpleado serviceEmpleado) {
		this.serviceEmpleado = serviceEmpleado;
	}
	
	@PostMapping
	public ResponseEntity<?> crear(@RequestBody Empleado empleado){
		try {
			Empleado e = serviceEmpleado.crear(empleado);
			return ResponseEntity.status(HttpStatus.CREATED).body(e);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el empleado");
		}
	}
	
	@GetMapping
	public ResponseEntity<?> listar(){
		try {
			List<Empleado> empleados = serviceEmpleado.listar();
			return ResponseEntity.ok(empleados);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al listar empleados");
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable int id){
		try {
			Empleado ee = serviceEmpleado.buscarPorId(id);
			if(ee == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empleado no encontrado");
			}
			return ResponseEntity.ok(ee);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al buscar empleado");
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> actualizar(@PathVariable int id,@RequestBody Empleado enuevo){
		try {
			Empleado ea = serviceEmpleado.actualizar(id, enuevo);
			if(ea == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empleado no encontrado");
			}
			
			return ResponseEntity.ok(ea);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar empleado"); 
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable int id){
		try {
			boolean ee = serviceEmpleado.eliminar(id);
			if(!ee) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empleado no encontrado");
			}
			return ResponseEntity.ok("Empleado eliminado");
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar empleado");
		}
	}
	
}
