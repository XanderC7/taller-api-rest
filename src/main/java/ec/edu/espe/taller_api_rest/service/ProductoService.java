package ec.edu.espe.taller_api_rest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ec.edu.espe.taller_api_rest.exception.ProductoInvalidoException;
import ec.edu.espe.taller_api_rest.exception.ProductoNoEncontradoException;
import ec.edu.espe.taller_api_rest.model.Producto;

@Service // Marca la clase para que Spring la administre en su contenedor
public class ProductoService {
    
    // Lista en memoria que simula la base de datos
    private final List<Producto> productos = new ArrayList<>();
    private Long generadorId = 1L;

    public List<Producto> obtenerTodos() {
        return productos;
    }

    public Producto obtenerPorId(Long id) {
        // Busca el producto, si no lo encuentra lanza la excepción
        return productos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ProductoNoEncontradoException("No existe el producto con ID: " + id));
    }

    public Producto registrar(Producto producto) {
        // Reglas de negocio
        if (producto.getPrecio() == null || producto.getPrecio() <= 0) {
            throw new ProductoInvalidoException("El precio del producto debe ser mayor a cero.");
        }
        if (producto.getStock() == null || producto.getStock() < 0) {
            throw new ProductoInvalidoException("El stock del producto no puede ser negativo.");
        }

        // Asignar ID simulado y guardar
        producto.setId(generadorId++);
        productos.add(producto);
        return producto;
    }

    public void eliminar(Long id) {
        Producto productoExistente = obtenerPorId(id); // Reutiliza el método que ya lanza la excepción si no existe
        productos.remove(productoExistente);
    }
}
