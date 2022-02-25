package com.springbook.TobySpring.one.two;

import com.springbook.TobySpring.one.one.User;
import com.springbook.TobySpring.one.one.UserDao;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class UserDaoTest {

    @Autowired
    UserDao dao;

    @Test
    public void addAndGet() throws SQLException {
        dao.deleteAll();

        User user = new User();
        user.setId("hwi");
        user.setName("휘도");
        user.setPassword("spring");

        dao.add(user);

        User user1 = dao.get(user.getId());

        assertThat(user1.getName()).isEqualTo(user.getName());
        assertThat(user1.getName()).isEqualTo(user.getName());
    }

}