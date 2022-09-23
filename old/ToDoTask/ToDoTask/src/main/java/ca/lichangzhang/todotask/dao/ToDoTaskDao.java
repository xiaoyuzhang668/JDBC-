package ca.lichangzhang.todotask.dao;

import ca.lichangzhang.todotask.dto.ToDo;
import java.util.List;

/**
 * Email: xiaoyuzhang668@gmail.com Date: 2022
 *
 * @author catzh
 */
public interface ToDoTaskDao {

    List<ToDo> getAll() throws ToDoPersistenceException;

    ToDo addItem(ToDo todo) throws ToDoPersistenceException;

    ToDo findItem(int id) throws ToDoPersistenceException;

    // true if item exists and is updated
    boolean updateItem(ToDo todo) throws ToDoPersistenceException;

    // true if item exists and is deleted
    boolean removeItem(int id) throws ToDoPersistenceException;
}
