
package com.cursojava.curso.dao;

import com.cursojava.curso.models.Estudiantes;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EstudianteDaoImpTest {

    @InjectMocks
    private EstudianteDaoImp estudianteDao;

    @Mock
    private EntityManager entityManager;

    @Mock
    private TypedQuery<Estudiantes> query;

    private AutoCloseable prueba;

    @BeforeEach
    void before() {
        prueba = MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetEstudiante() {
        Estudiantes esperado = new Estudiantes();
        esperado.setId(1L);

        when(entityManager.createQuery("FROM Estudiantes e WHERE e.id = :id", Estudiantes.class)).thenReturn(query);
        when(query.setParameter("id", 1L)).thenReturn(query);
        when(query.getSingleResult()).thenReturn(esperado);

        Estudiantes resultado = estudianteDao.getEstudiante(1L);
        assertEquals(1L, resultado.getId());
    }

    @Test
    void testGetEstudiantes() {
        List<Estudiantes> mockList = Arrays.asList(new Estudiantes(), new Estudiantes());
        when(entityManager.createQuery("FROM Estudiantes")).thenReturn(query);
        when(query.getResultList()).thenReturn(mockList);

        List<Estudiantes> resultado = estudianteDao.getEstudiantes();
        assertEquals(2, resultado.size());
    }

    @Test
    void testRegistrar() {
        Estudiantes estudiante = new Estudiantes();
        estudianteDao.registrar(estudiante);
        verify(entityManager).merge(estudiante);
    }

    @Test
    void testEditar() {
        Estudiantes estudiante = new Estudiantes();
        estudianteDao.editar(estudiante);
        verify(entityManager).merge(estudiante);
    }

    @Test
    void testEliminar() {
        Estudiantes estudiante = new Estudiantes();
        when(entityManager.find(Estudiantes.class, 1L)).thenReturn(estudiante);
        estudianteDao.eliminar(1L);
        verify(entityManager).remove(estudiante);
    }

    @Test
    void testEditarParcial() {
        Estudiantes estudiante = new Estudiantes();
        when(entityManager.find(Estudiantes.class, 2L)).thenReturn(estudiante);

        estudianteDao.editarParcial(2L, "prueba2@hotmail.com", "1234567890", "Español");

        assertEquals("prueba2@hotmail.com", estudiante.getEmail());
        assertEquals("1234567890", estudiante.getTelefono());
        assertEquals("Español", estudiante.getIdioma());
        verify(entityManager).merge(estudiante);
    }
}
