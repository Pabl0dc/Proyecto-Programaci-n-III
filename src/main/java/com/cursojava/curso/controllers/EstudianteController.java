package com.cursojava.curso.controllers;

import com.cursojava.curso.dao.EstudianteDao;
import com.cursojava.curso.models.Estudiantes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
public class EstudianteController {

    @Autowired
    private EstudianteDao estudianteDao;


    @RequestMapping(value = "api/estudiantes/{id}", method = RequestMethod.GET)
    public Estudiantes getEstudiante(@PathVariable Long id){
        return estudianteDao.getEstudiante(id);
    }

    @RequestMapping(value = "api/estudiantes", method = RequestMethod.GET)
    public List<Estudiantes> getEstudiantes(){

        return estudianteDao.getEstudiantes();
    }


    @RequestMapping(value = "api/estudiantes", method = RequestMethod.POST)
    public void registrarEstudiante(@RequestBody Estudiantes estudiantes){

        estudianteDao.registrar(estudiantes);
    }

    @PutMapping(value = "api/estudiantes/{id}")
    public void editar(@PathVariable Long id, @RequestBody Estudiantes estudiante) {
        estudiante.setId(id);
        estudianteDao.editar(estudiante);
    }

    @RequestMapping(value = "api/estudiantes/{id}", method = RequestMethod.PATCH)
    public void editarParcial(@PathVariable Long id, @RequestBody Estudiantes estudiante) {
        estudianteDao.editarParcial(id, estudiante.getEmail(), estudiante.getTelefono(), estudiante.getIdioma());
    }


    @RequestMapping(value = "api/estudiantes/{id}", method = RequestMethod.DELETE)
    public void eliminar(@PathVariable Long id){
        estudianteDao.eliminar(id);
    }


    //@RequestMapping(value = "estudiante")
    //public Estudiantes buscar(){
        //Estudiantes estudiante = new Estudiantes();
        //estudiante.setNombre("Pablo");
        //estudiante.setApellido("Del Cid");
        //estudiante.setEmail("pablo2005ale@gmail.com");
        //estudiante.setTelefono("5561 4105");
        //return estudiante;
    //}
}
