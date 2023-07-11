package org.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ReflectionUtil {

    Class<?> clz = null;

    /**
     *  특정 클래스의 특정 메소드 call 하기 (파라미터 string, return 없음)
     * */

    public static void callMethod(String classPackage, String className, String methodName, String str){
        try {
            Class<?> clz =  Class.forName(classPackage + "." + className);
            Method method = clz.getDeclaredMethod(methodName,String.class);           // int.class is parameter of method
            method.invoke(clz.newInstance(),str);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        }
        ;
    }

    /**
     *  특정 클래스의 특정 메소드 call 하기 (파라미터 int 두개, return 있음)
     * */

    public static void callMethod(String classPackage, String className, String methodName, int i, int j){
        try {
            Class<?> clz =  Class.forName(classPackage + "." + className);
            Method method = clz.getDeclaredMethod(methodName,int.class,int.class);           // int.class is parameter of method
            Object result = method.invoke(clz.newInstance(),i,j);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        }
        ;
    }

    /**
     *  retrieve all accessible field
     * */
    public static List<String> retreiveAcceesibleFields(String classPackage, String className, String methodName){
        List<String> fieldsList = new ArrayList<>();
        Class<?> clz = null;
        try {
            clz = Class.forName(classPackage + "." + className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Field[] f = clz.getDeclaredFields();
        for (int i = 0; i < f.length; i++) {
            fieldsList.add(f[i].getName());
        }
        return fieldsList;
    }

    /**
     *  retrieve all field
     * */
    public static List<String> retreiveAllFields(String classPackage, String className, String methodName){
        List<String> fieldsList = new ArrayList<>();
        Class<?> clz = null;
        try {
            clz = Class.forName(classPackage + "." + className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Field[] f = clz.getDeclaredFields();
        for (int i = 0; i < f.length; i++) {
            f[i].setAccessible(true);               // set true
            fieldsList.add(f[i].getName());
        }
        return fieldsList;
    }

    /**
     *  retrieve all method(including private)
     */
    public List<String> retreiveAllMethod(String classPackage, String className, String methodName){
        List<String> methodList = new ArrayList<>();
        Class<?> clz = null;
        try {
            clz = Class.forName(classPackage + "." + className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Method[] methods = clz.getDeclaredMethods();
        for(int i = 0; i < methods.length; i++){
            if(!methods[i].isAccessible()){                 // if private
                methods[i].setAccessible(true);
            }
            methodList.add(methods[i].getName());
        }
        return methodList;
    }
}
