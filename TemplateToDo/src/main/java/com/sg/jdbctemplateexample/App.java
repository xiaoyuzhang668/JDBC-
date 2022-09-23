/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.jdbctemplateexample;

import static java.lang.Integer.parseInt;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author kylerudy
 */
@SpringBootApplication
public class App implements CommandLineRunner {

    private static Scanner sc;
    @Autowired
    private JdbcTemplate jdbc;

    public static void main(String args[]) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        sc = new Scanner(System.in);

        do {
            System.out.println("To-Do List");
            System.out.println("1. Display List");
            System.out.println("2. Add Item");
            System.out.println("3. Update Item");
            System.out.println("4. Remove Item");
            System.out.println("5. Exit");
            String answer = (getNonDuplicate(4, 0, 9));
            System.out.println(answer);
//            findNumber("e:188:p:99");
            System.out.println(calculateResult("0974", "7812", "e:1:p:2"));

            System.out.println("Enter an option:");
            String option = sc.nextLine();
            try {
                switch (option) {
                    case "1":
                        displayList();
                        break;
                    case "2":
                        addItem();
                        break;
                    case "3":
                        updateItem();
                        break;
                    case "4":
                        removeItem();
                        break;
                    case "5":
                        System.out.println("Exiting");
                        System.exit(0);
                    default:
                        System.out.println("I don't understand");
                }
            } catch (Exception ex) {
                System.out.println("Error communicating with database");
                System.out.println(ex.getMessage());
                System.exit(0);
            }

        } while (true);
    }

    private void displayList() throws SQLException {
        List<ToDo> todos = jdbc.query("SELECT * FROM todo", new ToDoMapper());
        for (ToDo td : todos) {
            System.out.printf("%s: %s -- %s -- %s\n",
                    td.getId(),
                    td.getTodo(),
                    td.getNote(),
                    td.isFinished());
        }
        System.out.println("");
    }

    private void addItem() throws SQLException {
        System.out.println("Add Item");
        System.out.println("What is the task?");
        String task = sc.nextLine();
        System.out.println("Any additional notes?");
        String note = sc.nextLine();

        jdbc.update("INSERT INTO todo(todo, note) VALUES(?,?)", task, note);
        System.out.println("Add Complete");
    }

    private void updateItem() throws SQLException {
        System.out.println("Update Item");
        System.out.println("Which item do you want to update?");
        String itemId = sc.nextLine();
        ToDo item = jdbc.queryForObject("SELECT * FROM todo WHERE id = ?", new ToDoMapper(), itemId);

        System.out.println("1. ToDo - " + item.getTodo());
        System.out.println("2. Note - " + item.getNote());
        System.out.println("3. Finished - " + item.isFinished());
        System.out.println("What would you like to change?");
        String choice = sc.nextLine();
        switch (choice) {
            case "1":
                System.out.println("Enter new ToDo:");
                String todo = sc.nextLine();
                item.setTodo(todo);
                break;
            case "2":
                System.out.println("Enter new Note:");
                String note = sc.nextLine();
                item.setNote(note);
                break;
            case "3":
                System.out.println("Toggling Finished to " + !item.isFinished());
                item.setFinished(!item.isFinished());
                break;
            default:
                System.out.println("No change made");
                return;
        }
        jdbc.update("UPDATE todo SET todo = ?, note = ?, finished = ? WHERE id = ?",
                item.getTodo(),
                item.getNote(),
                item.isFinished(),
                item.getId());
        System.out.println("Update Complete");
    }

    private void removeItem() throws SQLException {
        System.out.println("Remove Item");
        System.out.println("Which item would you like to remove?");
        String itemId = sc.nextLine();
        jdbc.update("DELETE FROM todo WHERE id = ?", itemId);
        System.out.println("Remove Complete");
    }

    private static final class ToDoMapper implements RowMapper<ToDo> {

        @Override
        public ToDo mapRow(ResultSet rs, int index) throws SQLException {
            ToDo td = new ToDo();
            td.setId(rs.getInt("id"));
            td.setTodo(rs.getString("todo"));
            td.setNote(rs.getString("note"));
            td.setFinished(rs.getBoolean("finished"));
            return td;
        }
    }

    private static String getNonDuplicate(int size, int min, int max) {
        ArrayList numbers = new ArrayList();
        String answer = null;
        Random random = new Random();
        while (numbers.size() < size) {
            //Get Random numbers between range
            int randomNumber = random.nextInt((max - min) + 1) + min;
            //Check for duplicate values
            if (!numbers.contains(randomNumber)) {
                numbers.add(randomNumber);
            }
        }
        for (int i = 0; i < numbers.size(); i++) {
            answer = (answer + Integer.toString((int) numbers.get(i))).trim();
        }
        return answer;

    }

    private static void findNumber3(String result) {
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(result);

        while (m.find()) {
            System.out.print(m.group(1));

        }
    }

    private static List<String> findNumber2(String result) {
        LinkedList<String> numbers = new LinkedList<String>();
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(result);
        while (m.find()) {
            numbers.add(m.group());
        }
        return numbers;
    }

    private static String calculateResult(String guess, String answer, String result) {
        int exactMatch = findNumber(result).get(0);
        int partialMatch = findNumber(result).get(1);
        //if not exacatly match
        if (Objects.equals(guess, answer) == false) {
            for (int i = 0; i < 4; i++) {
                if (guess.charAt(i) == answer.charAt(i)) {
                    exactMatch++;
                } else {
                    if (guess.indexOf(answer.charAt(i)) != -1) {
                        partialMatch++;
                    }
                }
            }
            result = "e:" + exactMatch + ":p:" + partialMatch;
        }
        return result;
    }

    private static List<Integer> findNumber(String result) {
        LinkedList<Integer> numbers = new LinkedList<Integer>();
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(result);
        while (m.find()) {
            numbers.add(parseInt(m.group()));
        }
        return numbers;
    }
    
        private static String calculateResult(String guess, String answer) {
//        int exactMatch = findNumber(result).get(0);
//        int partialMatch = findNumber(result).get(1);
        int exactMatch = 0;
        int partialMatch = 0;
        String result = null;
        //if not exacatly match
        if (Objects.equals(guess, answer) == false) {
            for (int i = 0; i < 4; i++) {
                if (guess.charAt(i) == answer.charAt(i)) {
                    exactMatch++;
                } else {
                    if (guess.indexOf(answer.charAt(i)) != -1) {
                        partialMatch++;
                    }
                }
            }
            result = "e:" + exactMatch + ":p:" + partialMatch;
        }
        return result;
    }

    private static List<Integer> findNumber(String result) {
        LinkedList<Integer> numbers = new LinkedList<Integer>();
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(result);
        while (m.find()) {
            numbers.add(parseInt(m.group()));
        }
        return numbers;
    }
}
