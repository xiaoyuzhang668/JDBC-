package com.sg.jdbcexample.ui;

import com.sg.jdbcexample.dto.ToDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * Email: xiaoyuzhang668@gmail.com Date: 2022
 *
 * @author catzh
 */
@Component
public class ToDoView {

    @Autowired
    UserIO io;

    //====== CONTROLLER LAYER ======
    //display main menu
    public int printMenuAndGetSelection() {
        io.print("========================================================================================");
        io.print("******** Main Menu ********");
        io.print("---------------------------------------------------------------------------------------");
        io.print("To-Do List");
        io.print("1. Display List");
        io.print("2. Add Item");
        io.print("3. Update Item");
        io.print("4. Remove Item");
        io.print("5. Exit");

        io.print("============================================================");
        io.print("");

        return io.readInt("Please select the number of the opeation above you wish to perform: ", 1, 5);
    }
    
        public void displayPrintMenuAndGetSelection() {
        io.print("========================================================================================");
        io.print("******** Main Menu ********");
        io.print("---------------------------------------------------------------------------------------");
        io.print("To-Do List");
        io.print("1. Display List");
        io.print("2. Add Item");
        io.print("3. Update Item");
        io.print("4. Remove Item");
        io.print("5. Exit");

        io.print("============================================================");
        io.print("");

//        return io.readInt("Please select the number of the opeation above you wish to perform: ", 1, 5);
    }

    //ending and error  
    public void displayUnknownCommand() {
        io.print("Unknown Command!!");
    }

    public void displayExit() {
        io.print("Good Bye!!!");
    }
    
    public void displayError(String msg) {
        io.displayError(msg);
    }

    //====== DISPLAY ALL ======
    //display items banner
    public void displayAll() {
        io.print("========================================================================================");
        io.print("******** TO DO LIST ********");
    }

    public void displayItemFormat() {
        String fmt = "%-3s %-26s %26s \t%1%n";    //define display format 
        System.out.format(fmt, "Number", "Item Name", "Note", "Is Finished");
        System.out.format(fmt, "----------------------------", "-------------------------", "----------", "------------");
    }

    //====== ADD ITEM ======
    //display items banner
    public void displayAdd() {
        io.print("========================================================================================");
        io.print("******** ADD ITEM ********");
    }

    public ToDo addItem() {
        String todoName = io.readStringReq("What is the task?", "Task Name");
        io.displayBreak();
        String note = io.readString("Any additional notes?");
        io.displayBreak();
        boolean isFinished = true;

        ToDo todo = new ToDo(todoName, note, isFinished);
        return todo;
    }

    public void displayAddSuccess(ToDo addedItem) {
        io.print("Task ---" + addedItem.getTodo() + "--- has been successfully added.");
        io.displayEnter();
    }

    //====== UPDATE ITEM ======
    //display items banner
    public void displayUpdate() {
        io.print("========================================================================================");
        io.print("******** UPDATE ITEM ********");
    }

    public ToDo updateItem() {
        String todoName = io.readString("Any additional notes?");
        io.displayBreak();
        String note = io.readString("Any additional notes?");
        boolean isFinished = true;
        ToDo todo = new ToDo(todoName, note, isFinished);
        System.out.println("1. ToDo - " + todo.getTodo());
        System.out.println("2. Note - " + todo.getNote());
        System.out.println("3. Finished - " + todo.isFinished());
        System.out.println("What would you like to change?");

        return todo;
    }

    //display update item successfully
    public void displayUpdatedSuccess(ToDo updatedItem) {
        io.print("Task has been successfully updated.");
        io.displayEnter();
    }

    //====== REMOVE ITEM ======
    //display items banner
    public void displayRemove() {
        io.print("========================================================================================");
        io.print("******** REMOVE ITEM ********");
    }

    public void removeItem() {
        String todoName = io.readStringReq("Which item would you like to remove?", "Task Name");
        io.displayBreak();
        String note = io.readString("Any additional notes?");
        String isFinished = io.readStringRestrict("Is finished?", "true", "false", "finished?");
    }

    //display add item successfully
    public void displayRemoveSuccess(ToDo addedItem) {
        io.print("Task has been successfully removed.");
        io.displayEnter();
    }

}
