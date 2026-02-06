package com.codigo.api_bd_javier.service;

import com.codigo.api_bd_javier.dto.AlumnoCreateRequest;
import com.codigo.api_bd_javier.dto.AlumnoResponse;
import com.codigo.api_bd_javier.model.Alumno;
import com.codigo.api_bd_javier.repository.AlumnoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class AlumnoService {
    private final AlumnoRepository repository;

    public AlumnoService(AlumnoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public AlumnoResponse crear(AlumnoCreateRequest request){
        Alumno a = new Alumno(
                request.nombre(),
                request.edad());

        Alumno saved = repository.save(a);

        return new AlumnoResponse(
                saved.getId(),
                saved.getNombre(),
                saved.getEdad());
    }



    // LISTAR
    @Transactional(readOnly = true)
    public List<AlumnoResponse> listar() {
        return repository.findAll()
                .stream()
                .map(a -> new AlumnoResponse(
                        a.getId(),
                        a.getNombre(),
                        a.getEdad()
                ))
                .toList();
    }



    @Transactional(readOnly = true)
    public AlumnoResponse obtener(UUID id) {
        Alumno alumno = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));

        return new AlumnoResponse(
                alumno.getId(),
                alumno.getNombre(),
                alumno.getEdad()
        );
    }

    @Transactional
    public AlumnoResponse actualizar(UUID id, AlumnoCreateRequest request) {
        Alumno alumno = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));

        alumno.setNombre(request.nombre());
        alumno.setEdad(request.edad());

        Alumno updated = repository.save(alumno);

        return new AlumnoResponse(
                updated.getId(),
                updated.getNombre(),
                updated.getEdad()
        );
    }

    @Transactional
    public void eliminar(UUID id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Alumno no encontrado");
        }
        repository.deleteById(id);
    }


}
