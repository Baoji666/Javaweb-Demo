package cn.baoji.user.dao;

import cn.baoji.user.domain.User;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

/**
 * 数据类
 */
public class UserDao {
    //依赖数据文件即数据库
    private String path="C:\\Users\\Baoji\\Documents\\JavaWeb\\user.xml";

    //按用户名查询
    public User findUserByName(String username){
        /**
         * 1、得到Document
         * 2、xpath查询
         *  如果查询结果为null。返回null
         *  如果不为null需要把Element封装到User对象中
         */
        SAXReader reader=new SAXReader();
        try {
            Document doc=reader.read(path);
            Element ele=(Element)doc.selectSingleNode("//user[@username='"+username+"']");
            if(ele==null) return null;
            User user=new User();
            String attrUsername=ele.attributeValue("username");
            String attrPassword=ele.attributeValue("password");
            user.setUsername(attrUsername);
            user.setPassword(attrPassword);
            return user;

        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }



    }

    //添加用户
    public void add(User user){
        /**
         * 1、得到Document
         * 2、通过Document得到root元素  <users></users>
         * 3、使用参数user，转换成Element对象
         * 4、把Element添加到root元素
         * 5、保存Document
         */
        SAXReader reader=new SAXReader();
        try {
            Document doc=reader.read(path);
            Element root=doc.getRootElement();

            Element userEle=root.addElement("user");
            userEle.addAttribute("username",user.getUsername());
            userEle.addAttribute("password",user.getPassword());

            /**
             * 保存文档
             */
            OutputFormat format = OutputFormat.createPrettyPrint();
            XMLWriter writer = new XMLWriter(new OutputStreamWriter(
                    new FileOutputStream(path),"utf-8"), format);

            writer.write(doc);
            writer.close();//关流！！！

        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
