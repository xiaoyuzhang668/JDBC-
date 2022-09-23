package ca.lichangzhang.todotask;

import ca.lichangzhang.todotask.controller.ToDoController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**　Email: xiaoyuzhang668@gmail.com
 *   Date: 2022
 *
 *  @author catzh
 */
@SpringBootApplication
public class App implements CommandLineRunner {
    
    @Autowired
    ToDoController controller;

    public static void main(String args[]) {
        System.out.println("controller1");
        SpringApplication.run(App.class, args);
    }
    
    @Override
    public void run(String... args) throws Exception {
        System.out.println("controller2");
        controller.run();
        System.out.println("controller3");
    }
}

