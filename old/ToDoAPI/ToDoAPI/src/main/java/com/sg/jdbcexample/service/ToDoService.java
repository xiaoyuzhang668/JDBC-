package com.sg.jdbcexample.service;

import com.sg.jdbcexample.dao.ToDoPersistenceException;
import com.sg.jdbcexample.dto.ToDo;
import java.util.List;

/**
 * Email: xiaoyuzhang668@gmail.com Date: 2022
 *
 * @author catzh
 */
public interface ToDoService {

    List<ToDo> getAll() throws ToDoPersistenceException;

    ToDo addItem(ToDo todo) throws ToDoPersistenceException;

    ToDo findItem(int id) throws ToDoPersistenceException;

    // true if item exists and is updated
    boolean updateItem(ToDo todo)  throws ToDoPersistenceException;

    // true if item exists and is deleted
    boolean removeItem(int id) throws ToDoPersistenceException;

}
