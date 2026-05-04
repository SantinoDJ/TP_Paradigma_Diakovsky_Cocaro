package repositorio;
import java.util.List;

public interface IRepositorio<T> {
    void guardar(T t);
    T buscarPorId(Long id);
    List<T> listarTodos();
    void eliminar(Long id);
    void actualizar(T t);
}