package repositorio;
import java.util.List;
public interface IRepositorio<T> { // Interfaz genérica para un repositorio.

    void guardar(T t); // Guarda un objeto.

    T buscarPorId(Long id); // Busca un objeto por su ID.

    List<T> listarTodos(); // Devuelve todos los objetos.

    void eliminar(Long id); // Elimina un objeto por su ID.

    void actualizar(T t); // Actualiza un objeto.
}