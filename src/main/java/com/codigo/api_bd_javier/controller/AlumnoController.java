package com.codigo.api_bd_javier.controller;

import com.codigo.api_bd_javier.dto.AlumnoCreateRequest;
import com.codigo.api_bd_javier.dto.AlumnoResponse;
import com.codigo.api_bd_javier.service.AlumnoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/alumnos")
public class AlumnoController {
    private final AlumnoService alumnoService;

    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @PostMapping
    //@ResponseStatus(HttpStatus.CREATED)
    public AlumnoResponse crear(
            @Valid @RequestBody AlumnoCreateRequest request) {
        return alumnoService.crear(request);
    }


    //TAREA:
    //LISTAR
    @GetMapping
    public List<AlumnoResponse> listar() {
        return alumnoService.listar();
    }

    //CONSULTAR
    @GetMapping("/{id}")
    public AlumnoResponse obtener(
            @PathVariable UUID id) {
        return alumnoService.obtener(id);
    }


    //ACTUALZIAR
    @PutMapping("/{id}")
    public AlumnoResponse actualizar(
            @PathVariable UUID id,
            @Valid @RequestBody AlumnoCreateRequest request) {
        return alumnoService.actualizar(id, request);
    }

    //ELIMINAR
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(
            @PathVariable UUID id) {
        alumnoService.eliminar(id);
    }

}
