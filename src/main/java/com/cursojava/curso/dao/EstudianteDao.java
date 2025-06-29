package com.cursojava.curso.dao;

import com.cursojava.curso.models.Estudiantes;
import java.util.List;

public interface EstudianteDao {
    List<Estudiantes> getEstudiantes();

    Estudiantes getEstudiante(Long id);

    void eliminar(Long id);

    void registrar(Estudiantes estudiantes);

    void editar(Estudiantes estudiantes);

    void editarParcial(Long id, String email, String telefono, String idioma);
}
