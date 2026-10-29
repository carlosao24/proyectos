package com.krakedev.proyectos.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.krakedev.proyectos.entidades.Empleado;
import com.krakedev.proyectos.repositorios.EmpleadoRepository;

@Service
public class ServiceEmpleado {
	
	private final EmpleadoRepository empleadoRepository;
	
	public ServiceEmpleado(EmpleadoRepository  empleadoRepository) {
		this.empleadoRepository = empleadoRepository;
	}
	
	public Empleado crear(Empleado empleado){
		return empleadoRepository.save(empleado);
	}
	
	public List<Empleado> listar(){
		return empleadoRepository.findAll();
	}
	
	public Empleado buscarPorId(int id) {
		Optional<Empleado> resultado = empleadoRepository.findById(id);
		return resultado.orElse(null);
	}
	
	public Empleado actualizar(int id, Empleado enuevo) {
		Empleado ee = buscarPorId(id);
		
		if(ee == null) {
			return null;
		}
		ee.setNombre(enuevo.getNombre());
		ee.setCargo(enuevo.getCargo());
		ee.setTareas(enuevo.getTareas());
		
		return empleadoRepository.save(ee);
	}
	
	public boolean eliminar(int id) {
		Empleado ee = buscarPorId(id);
		if(ee == null) {
			return false;
		}
		empleadoRepository.deleteById(id);
		return true;
	}
	
}
