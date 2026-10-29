package com.krakedev.proyectos.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.krakedev.proyectos.entidades.Proyecto;
import com.krakedev.proyectos.repositorios.ProyectoRepository;

@Service
public class ServiceProyecto {
	
	private final ProyectoRepository proyectoRepository;
	
	public ServiceProyecto(ProyectoRepository proyectoRepository) {
		this.proyectoRepository = proyectoRepository;
	}
	
	public Proyecto crear(Proyecto proyecto) {
		return proyectoRepository.save(proyecto);
	}
	
	public List<Proyecto> listar(){
		return proyectoRepository.findAll();
	}
	
	public Proyecto buscarPorId(int id) {
		Optional<Proyecto> resultado = proyectoRepository.findById(id);
		return resultado.orElse(null);
	}
	
	public Proyecto actualizar(int id, Proyecto proyectoActualizado) {
		Proyecto p = buscarPorId(id);
		if(p == null) {
			return null;
		}
		p.setNombre(proyectoActualizado.getNombre());
		p.setDescripcion(proyectoActualizado.getDescripcion());
		p.setFechaInicio(proyectoActualizado.getFechaInicio());
		
		return proyectoRepository.save(p);	
	}
	
	public boolean eliminar(int id) {
		Proyecto p = buscarPorId(id);
		if(p == null) {
			return false;
		}
		proyectoRepository.deleteById(id);
		return true;
	}
	
	
}
