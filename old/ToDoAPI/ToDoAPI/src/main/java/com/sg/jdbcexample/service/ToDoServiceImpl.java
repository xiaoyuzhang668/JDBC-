package com.sg.jdbcexample.service;

import com.sg.jdbcexample.dao.ToDoDao;
import com.sg.jdbcexample.dao.ToDoInMemoryDao;
import com.sg.jdbcexample.dao.ToDoPersistenceException;
import com.sg.jdbcexample.dto.ToDo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Email: xiaoyuzhang668@gmail.com Date: 2022
 *
 * @author catzh
 */
public class ToDoServiceImpl implements ToDoService {

    @Autowired
    ToDoDao dao;

    @Autowired
    ToDoInMemoryDao inmemoryDao;

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public List<ToDo> getAll() throws ToDoPersistenceException {
        return dao.getAll();
    }

    @Override
    public ToDo addItem(ToDo todo) throws ToDoPersistenceException {
        return dao.addItem(todo);
    }

    @Override
    public ToDo findItem(int id) throws ToDoPersistenceException {
        return dao.findItem(id);
    }

    @Override
    public boolean updateItem(ToDo todo) throws ToDoPersistenceException {
        return dao.updateItem(todo);
    }

    @Override
    public boolean removeItem(int id) throws ToDoPersistenceException {
        return dao.removeItem(id);
    }

}
