
package com.cursojava.curso.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EstudiantesTest {



    @BeforeEach
    void before() {
        return;
    }

    @Test
    void testSettersAndGetters() {
        Estudiantes estudiante = new Estudiantes();
        estudiante.setId(1L);
        estudiante.setNombre("Lucy");
        estudiante.setApellido("Hernández");
        estudiante.setEmail("lucyyy@outlook.com");
        estudiante.setTelefono("1122334455");
        estudiante.setIdioma("Español");

        assertEquals(1L, estudiante.getId());
        assertEquals("Lucy", estudiante.getNombre());
        assertEquals("Hernández", estudiante.getApellido());
        assertEquals("lucyyy@outlook.com", estudiante.getEmail());
        assertEquals("1122334455", estudiante.getTelefono());
        assertEquals("Español", estudiante.getIdioma());
    }

    @Test
    void testToString() {
        Estudiantes estudiante = new Estudiantes();
        estudiante.setId(2L);
        estudiante.setNombre("Fredy");
        estudiante.setApellido("Del Cid");
        estudiante.setEmail("fredyyy@gmail.com");
        estudiante.setTelefono("1020304050");
        estudiante.setIdioma("Francés");

        String texto = estudiante.toString();
        assertTrue(texto.contains("Fredy"));
        assertTrue(texto.contains("Francés"));
    }
}
