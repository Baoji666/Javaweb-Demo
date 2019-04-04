package cn.baoji.user.dao;

import cn.baoji.user.domain.User;
import org.junit.Test;

public class UserDaoTest {
    @Test
    public void testFindByUserName(){
        UserDao userDao=new UserDao();
        User user=userDao.findUserByName("张三");
        System.out.println(user);
    }

    @Test
    public void testAdd(){
        UserDao userDao=new UserDao();

        User user=new User();
        user.setUsername("煞笔");
        user.setPassword("666");
        userDao.add(user);
    }
}
