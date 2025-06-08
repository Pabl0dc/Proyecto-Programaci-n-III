package com.cursojava.curso.dao;

import com.cursojava.curso.models.Estudiantes;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
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
    public List getEstudiantes() {
        String query = "FROM Estudiantes";
        return entityManager.createQuery(query).getResultList();
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

    @Override
    public Estudiantes obtenerEstudiantePorCredenciales(Estudiantes estudiantes){
        String query = "FROM Estudiantes WHERE email = :email";
        List<Estudiantes> lista = entityManager.createQuery(query)
                .setParameter("email", estudiantes.getEmail())
                .getResultList();

        if(lista.isEmpty()){
            return null;
        }

        String passwordHashed = lista.get(0).getIdioma();

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2d);
        if (argon2.verify(passwordHashed, estudiantes.getIdioma())){
            return lista.get(0);
        }
        return null;

    }
}
