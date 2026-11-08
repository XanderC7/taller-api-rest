package ec.edu.espe.taller_api_rest.exception;

// Hereda de RuntimeException para ser una excepción de negocio
public class ProductoNoEncontradoException extends RuntimeException {
    public ProductoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
