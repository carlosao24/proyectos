package com.krakedev.proyectos.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.krakedev.proyectos.entidades.Proyecto;
import com.krakedev.proyectos.entidades.Tarea;
import com.krakedev.proyectos.repositorios.ProyectoRepository;
import com.krakedev.proyectos.repositorios.TareaRepository;

@Service
public class ServiceTarea {
	
	private final TareaRepository tareaRepository;
	private final ProyectoRepository proyectoRepository;
	
	
	public ServiceTarea(TareaRepository tareaRepository, ProyectoRepository proyectoRepository) {
		this.tareaRepository = tareaRepository;
		this.proyectoRepository = proyectoRepository;
	}
	
	public Tarea crear(Tarea tarea) {
		if (tarea.getProyecto() == null) {
			throw new IllegalArgumentException("La tarea debe estar asociada a un proyecto");
		}
		int proyectoId = tarea.getProyecto().getId();
		Proyecto p = proyectoRepository.findById(proyectoId).orElse(null);
		if (p == null) {
			throw new RuntimeException("El proyecto con Id " + proyectoId + " no existe");
		}
		tarea.setProyecto(p);

		return tareaRepository.save(tarea);
	}
	
	public List<Tarea> listar(){
		return tareaRepository.findAll();
	}
	
	public Tarea buscarPorId(int id) {
		Optional<Tarea> resultado = tareaRepository.findById(id);
		return resultado.orElse(null);
	}
	
	public Tarea actualizar(int id, Tarea tnueva) {
		Tarea te = buscarPorId(id);
		if(te == null) {
			return null;
		}
		te.setDescripcion(tnueva.getDescripcion());
		te.setFechaLimite(tnueva.getFechaLimite());
		te.setCostoEstimado(tnueva.getCostoEstimado());
		return tareaRepository.save(te);
	}
	
	public boolean eliminar(int id) {
		Tarea te = buscarPorId(id);
		if(te == null) {
			return false;
		}
		tareaRepository.deleteById(id);
		return true;
	}
	
	
	
}
