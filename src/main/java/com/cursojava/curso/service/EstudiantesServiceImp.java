package com.cursojava.curso.service;

import com.cursojava.curso.dao.EstudianteDao;
import com.cursojava.curso.dao.EstudianteDaoImp;
import com.cursojava.curso.models.Estudiantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class EstudiantesServiceImp implements EstudianteService{

    @Autowired
    private EstudianteDao estudianteDao;

    @Override
    public Estudiantes getEstudiante(Long id){
        return estudianteDao.getEstudiante(id);

    }

//    @Override
//    public List<Estudiantes> getEstudiantes() {
//        String query = "FROM Estudiantes";
//        return entityManager.createQuery(query).getResultList();
//    }
//
//    @Override
//    public void eliminar(Long id) {
//        Estudiantes estudiantes = entityManager.find(Estudiantes.class, id);
//        entityManager.remove(estudiantes);
//    }
//
//    @Override
//    public void registrar(Estudiantes estudiantes) {
//        entityManager.merge(estudiantes);
//    }


}
