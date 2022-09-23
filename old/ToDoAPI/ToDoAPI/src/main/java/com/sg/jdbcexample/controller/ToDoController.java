package com.sg.jdbcexample.controller;

import com.sg.jdbcexample.dao.ToDoPersistenceException;
import com.sg.jdbcexample.dto.ToDo;
import com.sg.jdbcexample.service.ToDoService;
import com.sg.jdbcexample.ui.ToDoView;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Email: xiaoyuzhang668@gmail.com Date: 2022
 *
 * @author catzh
 */
//@RestController
//@RequestMapping("/api/todo")
@Component
public class ToDoController {

    @Autowired
    ToDoService service;

    @Autowired
    ToDoView view;
    
     

    public void run() {
        System.out.println("heloo");
        view.printMenuAndGetSelection();
        boolean keepGoing = true;
        view.displayPrintMenuAndGetSelection();
        try {

            while (keepGoing) {

                int menuSelection = getMenuSelection();//prompt user for selection

                switch (menuSelection) {
                    case 1:  //display all
                        getAll();
                        break;
                    case 2:
                        addItem();
                        break;
                    case 3:
                        updateItem();
                        break;
                    case 4:
                        removeItem();
                        break;
                    case 5:
                        keepGoing = false;  //exit loop
                        break;
                    default:
                        unknownCommand();
                }
            }
            exitMessage();
        } catch (ToDoPersistenceException e) {
            view.displayError(e.getMessage());
        }
    }

    private void getAll() throws ToDoPersistenceException {
        service.getAll();
    }

    private void addItem() throws ToDoPersistenceException {
        ToDo todo = view.addItem();
        service.addItem(todo);
    }

    private void updateItem() throws ToDoPersistenceException {
        ToDo todo = view.updateItem();
        service.updateItem(todo);
    }

    private void removeItem() throws ToDoPersistenceException {       
        service.removeItem(6);
    }

    //display unknown command
    private void unknownCommand() {
        view.displayUnknownCommand();
    }

    //display exit message
    private void exitMessage() {
        view.displayExit();
    }

//     get menu selection from daoview class, called from controller
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

//    @GetMapping
//    public void getAll() {
//        return service.getAll();
//    }
}
