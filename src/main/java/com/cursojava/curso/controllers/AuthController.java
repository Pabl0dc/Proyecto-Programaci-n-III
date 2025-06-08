package com.cursojava.curso.controllers;

import com.cursojava.curso.dao.EstudianteDao;
import com.cursojava.curso.models.Estudiantes;
import com.cursojava.curso.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private EstudianteDao estudianteDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody Estudiantes estudiantes){

        Estudiantes estudianteLogueado = estudianteDao.obtenerEstudiantePorCredenciales(estudiantes);
        if (estudianteLogueado != null){

            String tokenJwt = jwtUtil.create(String.valueOf(estudianteLogueado.getId()), estudianteLogueado.getEmail());

            return tokenJwt;
        }
        return "FAIL";
    }
}