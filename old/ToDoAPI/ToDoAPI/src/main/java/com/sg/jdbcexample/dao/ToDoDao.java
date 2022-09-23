/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.jdbcexample.dao;

import com.sg.jdbcexample.dto.ToDo;
import java.util.List;

/**
 *
 * @author catzh
 */
public interface ToDoDao {

    List<ToDo> getAll() throws ToDoPersistenceException;

    ToDo addItem(ToDo todo) throws ToDoPersistenceException;

    ToDo findItem(int id) throws ToDoPersistenceException;

     // true if item exists and is updated
    boolean updateItem(ToDo todo) throws ToDoPersistenceException;
    // true if item exists and is deleted
    boolean removeItem(int id) throws ToDoPersistenceException;
}
