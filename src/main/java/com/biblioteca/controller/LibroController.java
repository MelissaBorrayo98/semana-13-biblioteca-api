package com.biblioteca.controller;

import com.biblioteca.entity.Libro;
import com.biblioteca.repository.LibroRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final LibroRepository libroRepository;

    // Inyección por constructor
    public LibroController(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    // 1. Listar todos los libros (GET /api/libros)
    @GetMapping
    public ResponseEntity<List<Libro>> listarLibros() {
        List<Libro> libros = libroRepository.findAll();
        return ResponseEntity.ok(libros);
    }

    // 2. Buscar un libro por ID (GET /api/libros/{id})
    @GetMapping("/{id}")
    public ResponseEntity<Libro> buscarPorId(@PathVariable Long id) {
        Optional<Libro> libro = libroRepository.findById(id);
        if (libro.isPresent()) {
            return ResponseEntity.ok(libro.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // 3. Registrar un libro (POST /api/libros) -> 21 Created
    @PostMapping
    public ResponseEntity<Libro> registrarLibro(@RequestBody Libro libro) {
        Libro nuevoLibro = libroRepository.save(libro);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoLibro);
    }

    // 4. Actualizar completamente un libro (PUT /api/libros/{id})
    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizarLibro(@PathVariable Long id, @RequestBody Libro libroDetalles) {
        Optional<Libro> libroExistente = libroRepository.findById(id);
        if (libroExistente.isPresent()) {
            Libro libro = libroExistente.get();
            libro.setCodigo(libroDetalles.getCodigo());
            libro.setTitulo(libroDetalles.getTitulo());
            libro.setAutor(libroDetalles.getAutor());
            libro.setCategoria(libroDetalles.getCategoria());
            libro.setAnioPublicacion(libroDetalles.getAnioPublicacion());
            libro.setPrecio(libroDetalles.getPrecio());
            libro.setDisponible(libroDetalles.getDisponible());

            Libro libroActualizado = libroRepository.save(libro);
            return ResponseEntity.ok(libroActualizado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // 5. Eliminar un libro (DELETE /api/libros/{id}) -> 24 No Content
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable Long id) {
        if (libroRepository.existsById(id)) {
            libroRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // 6. Buscar por categoría (GET /api/libros/categoria/{categoria})
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Libro>> buscarPorCategoria(@PathVariable String categoria) {
        List<Libro> libros = libroRepository.findByCategoria(categoria);
        return ResponseEntity.ok(libros);
    }

    // 7. Listar libros disponibles (GET /api/libros/disponibles)
    @GetMapping("/disponibles")
    public ResponseEntity<List<Libro>> listarDisponibles() {
        List<Libro> libros = libroRepository.findByDisponible(true);
        return ResponseEntity.ok(libros);
    }
}