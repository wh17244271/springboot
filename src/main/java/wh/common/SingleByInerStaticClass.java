package wh.common;

import java.lang.reflect.Constructor;

public class SingleByInerStaticClass {

    private SingleByInerStaticClass(){
        System.out.println("无参数---构造----");
    }

    private static class InerClass{

        private final static  SingleByInerStaticClass instance = new SingleByInerStaticClass();


    }
    public static SingleByInerStaticClass getInstance(){
        return InerClass.instance;
    }



}

class test{
    public static void main(String[] args) throws NoSuchMethodException {
        SingleByInerStaticClass instance = SingleByInerStaticClass.getInstance();
        SingleByInerStaticClass instance1 = SingleByInerStaticClass.getInstance();
        System.out.println(instance==instance1);

        Class clazz = SingleByInerStaticClass.class;
        Constructor declaredConstructor = clazz.getDeclaredConstructor();
    }

}