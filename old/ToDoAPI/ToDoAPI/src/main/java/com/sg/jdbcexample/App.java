package com.sg.jdbcexample;

import com.sg.jdbcexample.controller.ToDoController;
import com.sg.jdbcexample.ui.ToDoView;
import com.sg.jdbcexample.ui.UserIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Email: xiaoyuzhang668@gmail.com Date: 2022
 *
 * @author catzh
 */
@SpringBootApplication
public class App implements CommandLineRunner {

    @Autowired
    ToDoController controller;    

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
     }

    @Override
    public void run(String... args) throws Exception {
        controller.run();
    }  
}
