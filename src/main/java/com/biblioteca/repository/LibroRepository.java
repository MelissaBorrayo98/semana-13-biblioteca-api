package com.biblioteca.repository;

import com.biblioteca.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {

    // Método derivado para buscar libros por categoría
    List<Libro> findByCategoria(String categoria);

    // Método derivado para consultar únicamente los libros disponibles
    List<Libro> findByDisponible(Boolean disponible);
}
