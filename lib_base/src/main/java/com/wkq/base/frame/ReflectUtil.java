package com.wkq.base.frame;


import android.util.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

public class ReflectUtil {

    private static final String TAG = "ReflectUtil";

    /**
     * 通过反射获取方法然后执行方法
     * @param obj
     * @param methodName
     * @param data
     * @param clazz
     * @return
     */
    public static Object invoke(Object obj, String methodName, Object data, Class clazz) {
        Object result = null;
        try {
            Method method = obj.getClass().getMethod(methodName, clazz);
            result = method.invoke(obj, data);
        } catch (Exception e) {
        }
        return result;
    }

    public static Object invoke(Object obj, String methodName, Object data) {
        Object result = null;
        try {
            Method method = obj.getClass().getMethod(methodName, new Class[]{data.getClass()});
            result = method.invoke(obj, data);
        } catch (Exception e) {
        }
        return result;
    }

    public static Object invoke(Object obj, String methodName) {
        Object result = null;
        try {
            Method method = obj.getClass().getMethod(methodName);
            result = method.invoke(obj);
        } catch (Exception e) {
        }
        return result;
    }

    /**
     * 通过反射和 class 创建 类对象
     * @param position
     * @param type
     * @param data
     * @param <R>
     * @return  这个方法 要求是创建的类必须手动创建构造方法
     */
    public static <R> R instance(int position, Class type, Object data) {
        R result = null;
        try {
            ParameterizedType pt = (ParameterizedType) type.getGenericSuperclass();
            Class<R> clazz = (Class<R>) pt.getActualTypeArguments()[position];
            Constructor constructor = clazz.getConstructor(new Class[]{data.getClass()});
            result = (R) constructor.newInstance(data);
        } catch (Exception e) {
            Log.d(TAG, "View Layer Unimplemented: Constructor (MvpActivity / MvpFragment)", e);
        }
        return result;
    }

    /**
     * 通过反射创建数据对象
     * @param position
     * @param object
     * @param <R>
     * @return
     */
    public static <R> R instance(int position, Object object) {
        R result = null;
        try {
            //获取所有泛型的数据
            ParameterizedType pt = (ParameterizedType) object.getClass().getGenericSuperclass();
            //获取泛型的数据
            Class<R> clazz = (Class<R>) pt.getActualTypeArguments()[position];
            //创建一个泛型类型的变量
            result = clazz.newInstance();
        } catch (Exception e) {
            Log.d(TAG, "Presenter or data need to implement the default constructor: constructor ()", e);
        }
        return result;
    }


    public static <R> R instanceInterFaceClass(int position, Object object) {
        R result = null;
        try {
            //获取所有泛型的数据
            ParameterizedType pt = (ParameterizedType) object.getClass().getGenericSuperclass();
            //获取泛型的数据
            Class<R> clazz = (Class<R>) pt.getActualTypeArguments()[position];
            result=  clazz.newInstance();
        } catch (Exception e) {
            Log.d(TAG, "Presenter or data need to implement the default constructor: constructor ()", e);
        }
        return result;
    }
}
