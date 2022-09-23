package com.sg.jdbcexample.dao;

import com.sg.jdbcexample.dto.ToDo;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

/**　Email: xiaoyuzhang668@gmail.com
 *   Date: 2022
 *
 *  @author catzh
 */
@Repository
public class ToDoInMemoryDao implements ToDoDao {
    
    private static final List<ToDo> todos = new ArrayList<>();

    @Override
    public List<ToDo> getAll() throws ToDoPersistenceException {
        return new ArrayList<>(todos);
    }

    @Override
    public ToDo addItem(ToDo todo) throws ToDoPersistenceException {
        
        int nextId = todos.stream()
                   .mapToInt(i -> i.getId())
                   .max()
                   .orElse(0) + 1;
                   
            todo.setId (nextId);
            todos.add(todo);
            return todo;      
    }

    @Override
    public ToDo findItem(int id) throws ToDoPersistenceException {
       return todos.stream()
               .filter(i -> i.getId() == id)
               .findFirst()
               .orElse(null);
    }

    @Override
    public boolean updateItem(ToDo todo) throws ToDoPersistenceException {
       int index = 0;
       while (index < todos.size()
               && todos.get(index).getId() != todo.getId()) {
           index++;
       }
       
       if (index < todos.size()) {
           todos.set(index, todo);
       }
       
       return index < todos.size();
    }

    @Override
    public boolean removeItem(int id) throws ToDoPersistenceException {
        return todos.removeIf(i -> i.getId() == id);
    }
}
