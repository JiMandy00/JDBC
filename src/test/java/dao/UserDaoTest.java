package dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    @Autowired
    ApplicationContext context;

    @Test
    void add() {
    }

    @Test
    void get() {
    }

    @Test
    void deleteAll() {
    }

    @Test
    void count() throws SQLException, ClassNotFoundException {
        User user1 = new User("1", "BYEON", "1234");
        User user2 = new User("2", "JI", "password");
        User user3 = new User("3", "HWAN", "123qwe");

        UserDao userDao = context.getBean("awsUserDao", UserDao.class);
        userDao.deleteAll();
        assertEquals(0, userDao.getCountAll());

        userDao.add(user1);
        assertEquals(1, userDao.getCountAll());
        userDao.add(user2);
        assertEquals(2, userDao.getCountAll());
        userDao.add(user3);
        assertEquals(3, userDao.getCountAll());
    }

    @Test
    void addAndGet() throws SQLException, ClassNotFoundException {
        User user1 = new User("1", "BYEON", "1234");

        UserDao userDao = context.getBean("awsUserDao", UserDao.class);
        userDao.deleteAll();
        assertEquals(0, userDao.getCountAll());

        userDao.add(user1);
        assertEquals(1, userDao.getCountAll());
        User user = userDao.findById(user1.getId());

        assertEquals(user1.getName(), user.getName());
        assertEquals(user1.getPassword(), user.getPassword());
    }
}