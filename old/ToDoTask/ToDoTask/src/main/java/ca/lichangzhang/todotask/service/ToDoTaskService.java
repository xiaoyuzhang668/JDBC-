package ca.lichangzhang.todotask.service;

import ca.lichangzhang.todotask.dao.ToDoPersistenceException;
import ca.lichangzhang.todotask.dto.ToDo;
import java.util.List;

/**　Email: xiaoyuzhang668@gmail.com
 *   Date: 2022
 *
 *  @author catzh
 */
public interface ToDoTaskService {
    List<ToDo> getAll() throws ToDoPersistenceException;

    ToDo addItem(ToDo todo) throws ToDoPersistenceException;

    ToDo findItem(int id) throws ToDoPersistenceException;

    // true if item exists and is updated
    boolean updateItem(ToDo todo)  throws ToDoPersistenceException;

    // true if item exists and is deleted
    boolean removeItem(int id) throws ToDoPersistenceException;
}
