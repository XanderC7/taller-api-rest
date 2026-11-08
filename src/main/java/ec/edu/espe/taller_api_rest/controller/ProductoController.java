package ec.edu.espe.taller_api_rest.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.espe.taller_api_rest.model.Producto;
import ec.edu.espe.taller_api_rest.service.ProductoService;

@RestController // Indica que esta clase atiende peticiones REST y devuelve JSON
@RequestMapping("/api/productos") // Ruta base del endpoint
public class ProductoController {

    private final ProductoService productoService;

    // Inyeccion de dependencias por contructor
    // Controller no usa "new ProductoService()", Spring le entrega la instancia
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public ResponseEntity<List<Producto>> listarTodos() {
        return new ResponseEntity<>(productoService.obtenerTodos(), HttpStatus.OK); // 200 OK
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerPorId(@PathVariable Long id) {
        return new ResponseEntity<>(productoService.obtenerPorId(id), HttpStatus.OK); // 200 OK
    }

    @PostMapping
    public ResponseEntity<Producto> registrar(@RequestBody Producto producto) {
        Producto nuevoProducto = productoService.registrar(producto);
        return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED); // 201 Created
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
    }
}
