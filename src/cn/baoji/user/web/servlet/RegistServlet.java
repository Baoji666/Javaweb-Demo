package cn.baoji.user.web.servlet;

import cn.baoji.user.commons.CommonUtils;
import cn.baoji.user.domain.User;
import cn.baoji.user.service.UserException;
import cn.baoji.user.service.UserService;
import com.google.code.kaptcha.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RegistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        //依赖UserService
        UserService userService=new UserService();


        //封装表单数据（封装到User对象中）
        User form=CommonUtils.toBean(request.getParameterMap(), User.class);


        //任务：表单校验
        /**
         * 1、创建一个map，用来装载所有表单错误信息
         * 在校验过程中，如果失败，向map添加错误信息，其中key为表单字段名称
         * 2、教研之后，查看map长度是否大于0，若大于0，说明有错误信息
         *      则保存map到request，保存form到request，转发到regist.jsp
         *      若map为空，说明没错误，向下执行
         */
        Map<String,String> errors=new HashMap<>();

        String username=form.getUsername();
        String regex1="\\S{3,15}";
        if(!username.matches(regex1)){
            errors.put("username","用户名长度必须在3~15之间");
        }

        String password=form.getPassword();
        String regex2="\\S{3,15}";
        if(!password.matches(regex2)){
            errors.put("password","密码长度必须在3~15之间");
        }

        //任务：校验验证码
        /**
         * 用户填写的验证码已经封装到user中
         * 从session中获取真正的验证码
         * 比较两者
         * 若不同，保存错误信息、保存表单数据，转发到regist.jsp
         * 相同，继续向下执行
         */

        //KaptchaServlet会自动把验证码设置到session中。能够以下方式获取生成的验证码
        String realcode = (String)request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);

        if(!realcode.equalsIgnoreCase(form.getVerifyCode())){
            errors.put("verifyCode","验证码错误");
        }




        if(!errors.isEmpty()){
            request.setAttribute("errors",errors);
            request.setAttribute("user",form);
            request.getRequestDispatcher("/user/regist.jsp").forward(request,response);
            return;
        }





        //调用UserService的regist()方法，传递form过去
        //若得到异常，获取异常信息，保存到request域，转发到regist.jsp中显示
        //没有异常：输出注册成功！
        try{
            userService.regist(form);
            response.getWriter().print("<h1>注册成功！</h1><a href='"+
                    request.getContextPath()+"/user/login.jsp'>点击这里完成登录</a>");

        }catch (UserException e){
            request.setAttribute("msg",e.getMessage());
            request.setAttribute("user",form);
            request.getRequestDispatcher("/user/regist.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
