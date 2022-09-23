package com.sg.jdbcexample.dao;

/**　Email: xiaoyuzhang668@gmail.com
 *   Date: 2022
 *
 *  @author catzh
 */
public class ToDoPersistenceException extends Exception {
    public ToDoPersistenceException(String message) {
        super(message);
    }
  
    public ToDoPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
