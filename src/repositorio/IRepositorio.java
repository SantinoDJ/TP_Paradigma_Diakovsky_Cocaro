package repositorio;
import java.util.List;

// Interfaz para manejar el guardado y búsqueda de datos
public interface IRepositorio<T> {

    // Guardar nuevo objeto
    void guardar(T t);

    // Buscar por ID
    T buscarPorId(Long id);

    // Listar todos los guardados
    List<T> listarTodos();

    // Eliminar por ID
    void eliminar(Long id);

    // Actualizar datos de un objeto
    void actualizar(T t);
}