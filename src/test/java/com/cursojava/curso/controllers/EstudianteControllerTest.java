
package com.cursojava.curso.controllers;

import com.cursojava.curso.dao.EstudianteDao;
import com.cursojava.curso.models.Estudiantes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EstudianteControllerTest {

    @InjectMocks
    private EstudianteController estudianteController;

    @Mock
    private EstudianteDao estudianteDao;

    private AutoCloseable prueba;

    @BeforeEach
    void before() {
        prueba = MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetEstudiante() {
        Estudiantes esperado = new Estudiantes();
        esperado.setId(1L);
        esperado.setNombre("Carlos");

        when(estudianteDao.getEstudiante(1L)).thenReturn(esperado);

        Estudiantes resultado = estudianteController.getEstudiante(1L);
        assertNotNull(resultado);
        assertEquals("Carlos", resultado.getNombre());
    }

    @Test
    void testGetEstudiantes() {
        Estudiantes e1 = new Estudiantes();
        e1.setId(1L);
        e1.setNombre("Ana");

        Estudiantes e2 = new Estudiantes();
        e2.setId(2L);
        e2.setNombre("Luis");

        when(estudianteDao.getEstudiantes()).thenReturn(Arrays.asList(e1, e2));

        List<Estudiantes> lista = estudianteController.getEstudiantes();
        assertEquals(2, lista.size());
    }

    @Test
    void testRegistrarEstudiante() {
        Estudiantes nuevo = new Estudiantes();
        nuevo.setNombre("Pedro");

        estudianteController.registrarEstudiante(nuevo);
        verify(estudianteDao, times(1)).registrar(nuevo);
    }

    @Test
    void testEditar() {
        Estudiantes e = new Estudiantes();
        e.setNombre("Mario");

        estudianteController.editar(5L, e);
        assertEquals(5L, e.getId());
        verify(estudianteDao).editar(e);
    }

    @Test
    void testEditarParcial() {
        Estudiantes parcial = new Estudiantes();
        parcial.setEmail("prueba@gmail.com");
        parcial.setTelefono("1234567890");
        parcial.setIdioma("Español");

        estudianteController.editarParcial(3L, parcial);
        verify(estudianteDao).editarParcial(3L, "prueba@gmail.com", "1234567890", "Español");
    }

    @Test
    void testEliminar() {
        estudianteController.eliminar(10L);
        verify(estudianteDao).eliminar(10L);
    }
}
