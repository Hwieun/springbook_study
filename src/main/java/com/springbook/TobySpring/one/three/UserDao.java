package com.springbook.TobySpring.one.three;

import com.springbook.TobySpring.one.one.User;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    JdbcContext jdbcContext;

    public void setJdbcContext(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public void add(final User user) throws SQLException {
        jdbcContext.executeUpdate(c -> {
            PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?,?,?)");

            ps.setString(1, user.getId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getPassword());
            return ps;
        });
    }

    public User get(String id) throws SQLException {
        StatementStrategy st = new StatementStrategy() {
            @Override
            public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
                PreparedStatement ps = c.prepareStatement("select * from users where id = ?");
                ps.setString(1, id);
                return ps;
            }
        };
        User user = jdbcContext.executeQuery(st, User.class);
//        ResultSet resultSet = ps.executeQuery();
//        if (resultSet.next()) {
//            user = new User();
//            user.setId(resultSet.getString("id"));
//            user.setName(resultSet.getString("name"));
//            user.setPassword(resultSet.getString("password"));
//        }
        return user;
    }

    public void deleteAll() throws SQLException {
        jdbcContext.executeSql("delete from users");
    }

}
