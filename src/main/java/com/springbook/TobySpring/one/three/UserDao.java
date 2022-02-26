package com.springbook.TobySpring.one.three;

import com.springbook.TobySpring.one.one.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDao {

    //    jdbcTemplate jdbcTemplate;
    JdbcTemplate jdbcTemplate;
    private RowMapper userMapper = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            rs.next();
            User user = new User();
            user.setId(rs.getString("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            return user;
        }
    };

    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void add(final User user) throws SQLException {
        jdbcTemplate.update("insert into users(id, name, password) values(?,?,?)", user.getId(), user.getName(), user.getPassword());
    }

    public User get(String id) throws SQLException {
// (1)
//        StatementStrategy st = new StatementStrategy() {
//            @Override
//            public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
//                PreparedStatement ps = c.prepareStatement("select * from users where id = ?");
//                ps.setString(1, id);
//                return ps;
//            }
//        };
//        User user = jdbcContext.executeQuery(st, User.class);
//        ResultSet resultSet = ps.executeQuery();
//        if (resultSet.next()) {
//            user = new User();
//            user.setId(resultSet.getString("id"));
//            user.setName(resultSet.getString("name"));
//            user.setPassword(resultSet.getString("password"));
//        }

// (2)
        jdbcTemplate.queryForObject("select * from users where id = ?",
                new Object[]{id},
                // 중복
                new RowMapper<User>() {
                    @Override
                    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                        rs.next();
                        User user = new User();
                        user.setId(rs.getString("id"));
                        user.setName(rs.getString("name"));
                        user.setPassword(rs.getString("password"));
                        return user;
                    }
                });


// (3)
        jdbcTemplate.queryForObject("select * from users where id = ?", new Object[]{id}, userMapper);


// (2.1)
        return jdbcTemplate.query(con -> con.prepareStatement("select * from users where id = ?"),
                new ResultSetExtractor<User>() {
                    @Override
                    public User extractData(ResultSet rs) throws SQLException, DataAccessException {
                        rs.next();
                        User user = new User();
                        user.setId(rs.getString("id"));
                        user.setName(rs.getString("name"));
                        user.setPassword(rs.getString("password"));
                        return user;
                    }
                });
    }

    public List<User> getAll() throws SQLException {
       return jdbcTemplate.query("select * from users order by id", userMapper);
    }

    public void deleteAll() throws SQLException {
        jdbcTemplate.update("delete from users");
    }

}
