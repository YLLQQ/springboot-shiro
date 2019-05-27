package org.yang.springboot.shiro.util;

import org.apache.shiro.util.Assert;

/**
 * org.yang.springboot.shiro.util.ClassUtil
 *
 * @author eleven
 * @date 2019/05/27
 */
public class ClassUtil {

    public static <T> T initByClassPath(String classPath) {
        T t = null;

        try {
            Class<?> tClass = Class.forName(classPath);
            t = (T) tClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Assert.notNull(t, "cannot init object with " + classPath);

        return t;
    }
}
