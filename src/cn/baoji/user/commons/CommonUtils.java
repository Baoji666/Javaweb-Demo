package cn.baoji.user.commons;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;
import java.util.UUID;

public class CommonUtils {

    /**
     * 生成uuid
     * @return
     */
    public static String uuid(){
        return UUID.randomUUID().toString().replace("-","").toUpperCase();
    }

    /**
     * 把map转换成指定类型的JavaBean对象
     * @param map
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T toBean(Map map,Class<T> clazz){
        try{
            T bean=clazz.newInstance();
            BeanUtils.populate(bean,map);
            return bean;
        }catch (Exception e){
            throw  new RuntimeException(e);
        }
    }
}
