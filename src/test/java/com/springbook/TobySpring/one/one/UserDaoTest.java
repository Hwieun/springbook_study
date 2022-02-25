package com.springbook.TobySpring.one.one;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDaoTest {
    UserDao dao;

    @Before
    private void setUp() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        dao = context.getBean("userDao", UserDao.class);
    }

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