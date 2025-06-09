package com.cursojava.curso.dao;

import com.cursojava.curso.models.Estudiantes;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class EstudianteDaoImp implements EstudianteDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Estudiantes getEstudiante(Long id) {
        String query = "FROM Estudiantes e WHERE e.id = :id";
        return entityManager.createQuery(query, Estudiantes.class).setParameter("id", id).getSingleResult();
    }

    @Override
    public List<Estudiantes> getEstudiantes() {
        String query = "FROM Estudiantes";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void editar(Estudiantes estudiantes) {
        entityManager.merge(estudiantes);
    }

    @Override
    public void editarParcial(Long id, String email, String telefono, String idioma) {
        Estudiantes estudiante = entityManager.find(Estudiantes.class, id);
        if (estudiante != null) {
            estudiante.setEmail(email);
            estudiante.setTelefono(telefono);
            estudiante.setIdioma(idioma);
            entityManager.merge(estudiante);
        }
    }

    @Override
    public void eliminar(Long id) {
        Estudiantes estudiantes = entityManager.find(Estudiantes.class, id);
        entityManager.remove(estudiantes);
    }

    @Override
    public void registrar(Estudiantes estudiantes) {
        entityManager.merge(estudiantes);
    }

}
