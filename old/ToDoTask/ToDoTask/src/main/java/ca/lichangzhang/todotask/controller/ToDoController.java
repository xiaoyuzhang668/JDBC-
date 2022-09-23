package ca.lichangzhang.todotask.controller;

import ca.lichangzhang.todotask.service.ToDoTaskService;
import ca.lichangzhang.todotask.ui.ToDoTaskView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Email: xiaoyuzhang668@gmail.com Date: 2022
 *
 * @author catzh
 */
@Component
public class ToDoController {

    @Autowired
    ToDoTaskService service;

    @Autowired
    ToDoTaskView view;

    public void run() {
        System.out.println("hello");    
        boolean keepGoing = true;
       
        try {

            while (keepGoing) {

                int menuSelection = getMenuSelection();//prompt user for selection

                switch (menuSelection) {
                    case 1:  //display all
//                        getAll();
                        break;
                    case 2:
//                        addItem();
                        break;
                    case 3:
//                        updateItem();
                        break;
                    case 4:
//                        removeItem();
                        break;
                    case 5:
                        keepGoing = false;  //exit loop
                        break;
                    default:
//                        unknownCommand();
                }
            }
//            exitMessage();
        } catch (Exception e) {
//            view.displayError(e.getMessage());
        }
    }

//    private void getAll() throws ToDoPersistenceException {
//        service.getAll();
//    }
//
//    private void addItem() throws ToDoPersistenceException {
//        ToDo todo = view.addItem();
//        service.addItem(todo);
//    }
//
//    private void updateItem() throws ToDoPersistenceException {
//        ToDo todo = view.updateItem();
//        service.updateItem(todo);
//    }
//
//    private void removeItem() throws ToDoPersistenceException {
//        service.removeItem(6);
//    }
//
//    //display unknown command
//    private void unknownCommand() {
//        view.displayUnknownCommand();
//    }
//
//    //display exit message
//    private void exitMessage() {
//        view.displayExit();
//    }
//
////     get menu selection from daoview class, called from controller
    private int getMenuSelection() {
        return 0;
    }
}
