package com.sg.jdbcexample.dao;

import org.springframework.jdbc.core.RowMapper;
import com.sg.jdbcexample.dao.ToDoDao;
import com.sg.jdbcexample.dto.ToDo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

/**
 * Email: xiaoyuzhang668@gmail.com Date: 2022
 *
 * @author catzh
 */
@Repository
public class ToDoDatabaseDao implements ToDoDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ToDoDatabaseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ToDo> getAll() throws ToDoPersistenceException {
        final String sql = "SELECT id, todo, note, finished FROM todoDB;";
        List<ToDo> todos = jdbcTemplate.query(sql, new ToDoMapper());
        for (ToDo todo : todos) {
            System.out.printf("%s: %s -- %s -- %s\n", 
                    todo.getId(),
                    todo.getTodo(),
                    todo.getNote(),
                    todo.isFinished());
        }
        System.out.println("");
        return jdbcTemplate.query(sql, new ToDoMapper());
    }

    @Override
    public ToDo addItem(ToDo todo) throws ToDoPersistenceException {
        final String sql = "INSERT INTO todoDB(todo, note, finished) VALUES (?, ?, ?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {
            PreparedStatement statement = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, todo.getTodo());
            statement.setString(2, todo.getNote());
            statement.setBoolean(3, todo.isFinished());
            return statement;

        }, keyHolder);

        todo.setId(keyHolder.getKey().intValue());

        return todo;
    }

    @Override
    public ToDo findItem(int id) throws ToDoPersistenceException {
        final String sql = "SELECT id, todo, note, finished "
                + "FROM todoDB WHERE id = ?; ";

        return jdbcTemplate.queryForObject(sql, new ToDoMapper(), id);
    }

    @Override
    public boolean updateItem(ToDo todo)  throws ToDoPersistenceException {
        final String sql = "UPDATE todoDB SET"
                + "todo = ?, "
                + "note = ?,  "
                + "finished = ? "
                + "WHERE id = ?; ";

        return jdbcTemplate.update(sql,
                todo.getTodo(),
                todo.getNote(),
                todo.isFinished(),
                todo.getId()) > 0;
    }

    @Override
    public boolean removeItem(int id) throws ToDoPersistenceException {
        final String sql = "DELETE FROM todo WHERE id = ?; ";
        return jdbcTemplate.update(sql, id) > 0;
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

}
