package repositorio;

import java.util.List;

public interface IRepositorio<T> {
    void guardar(T t);
    List<T> buscarTodos();
    T buscarPorId(Integer id);
    void eliminar(Integer id);
}
