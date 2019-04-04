package cn.baoji.user.service;

import cn.baoji.user.dao.UserDao;
import cn.baoji.user.domain.User;

public class UserService {
    private UserDao userDao=new UserDao();

    public User login(User form) throws UserException{
        /**
         * 登录功能
         * 1、使用form中的username进行查询，得到User user
         * 2、如果返回null，说明用户不存在，抛出异常
         * 3、如果不为null，比较user的password和form的password，如果不同，抛出异常
         */
        User user=userDao.findUserByName(form.getUsername());
        if(user==null) throw new UserException("用户名不存在！");
        if(!form.getPassword().equals(user.getPassword()))
            throw new UserException("密码错误");
        return user;
    }

    public void regist(User form) throws UserException{
        /**
         * 注册功能
         * 1、使用用户名去查询，如果返回null，完成添加
         * 2、如果返回不是null，抛出异常【用户名已注册】
         */
        User user=userDao.findUserByName(form.getUsername());
        if(user!=null) throw new UserException("用户名"+form.getUsername()+"已被注册！");

        userDao.add(form);


    }
}
