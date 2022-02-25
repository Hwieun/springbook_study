package com.springbook.TobySpring.one.six.user;

import com.springbook.TobySpring.one.six.TransactionHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.assertj.ApplicationContextAssert;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.PlatformTransactionManager;

import javax.transaction.TransactionManager;
import java.lang.reflect.Proxy;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    @Autowired
    ApplicationContext context;
    @Autowired
    PlatformTransactionManager transactionManager;

    @Test
    @DirtiesContext // context 무효화
    public void upgradeAllOrNothing() {
        UserService testUserService = new UserServiceImpl();

        TransactionHandler txHandler = new TransactionHandler();
        txHandler.setTarget(testUserService);
        txHandler.setTransactionManager(transactionManager);
        txHandler.setPattern("upgradeLevels");
        UserService txUserService = (UserService) Proxy.newProxyInstance(
                getClass().getClassLoader(),
                new Class[]{UserService.class},
                txHandler
        );

        txUserService.upgradeLevels();
    }

}