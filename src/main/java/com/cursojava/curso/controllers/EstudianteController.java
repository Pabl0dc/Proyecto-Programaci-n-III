package com.cursojava.curso.controllers;

import com.cursojava.curso.dao.EstudianteDao;
import com.cursojava.curso.models.Estudiantes;
import com.cursojava.curso.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EstudianteController {

    @Autowired
    private EstudianteDao estudianteDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/usuario/{id}", method = RequestMethod.GET)
    public Estudiantes getEstudiante(@PathVariable Long id){
        Estudiantes estudiantes = new Estudiantes();
        estudiantes.setId(id);
        estudiantes.setNombre("Pablo");
        estudiantes.setApellido("Del Cid");
        estudiantes.setEmail("pablo2005ale@gmail.com");
        estudiantes.setTelefono("5561 4105");
        return estudiantes;
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.GET)
    public List<Estudiantes> getEstudiantes(@RequestHeader(value = "Authorization") String token){
        if(!validadToken(token)){return null;}


        return estudianteDao.getEstudiantes();
    }

    private boolean validadToken(String token){
        String usuarioId = jwtUtil.getKey(token);
        return usuarioId != null;
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.POST)
    public void registrarUsuario(@RequestBody Estudiantes estudiantes){

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2d);
        String hash = argon2.hash(1, 1024, 1, estudiantes.getIdioma());
        estudiantes.setIdioma(hash);

        estudianteDao.registrar(estudiantes);
    }

    @RequestMapping(value = "usuario3")
    public Estudiantes editar(){
        Estudiantes estudiantes = new Estudiantes();
        estudiantes.setNombre("Pablo");
        estudiantes.setApellido("Del Cid");
        estudiantes.setEmail("pablo2005ale@gmail.com");
        estudiantes.setTelefono("5561 4105");
        return estudiantes;
    }

    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.DELETE)
    public void eliminar(@RequestHeader(value = "Authorization") String token, @PathVariable Long id){
        if(!validadToken(token)){return;}
        estudianteDao.eliminar(id);
    }

    //@RequestMapping(value = "usuario1")
    //public Estudiantes buscar(){
        //Estudiantes usuario = new Estudiantes();
        //usuario.setNombre("Pablo");
        //usuario.setApellido("Del Cid");
        //usuario.setEmail("pablo2005ale@gmail.com");
        //usuario.setTelefono("5561 4105");
        //return usuario;
    //}
}
