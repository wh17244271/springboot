package wh.common;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public enum SingleByEnum {

    INSTANCE;

    SingleByEnum() {
    }


    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "SingleByEnum{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

class test2 {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        SingleByEnum instance = SingleByEnum.INSTANCE;
        SingleByEnum instance1 = SingleByEnum.INSTANCE;
        System.out.println(instance.toString());
        System.out.println(instance1.toString());
        System.out.println(instance1 == instance);
        instance.setAge(18);
        System.out.println(instance.toString());
        instance1.setName("wh");
        System.out.println(instance1.toString());

        Class clazz = SingleByEnum.class;
        Constructor c0 = clazz.getDeclaredConstructor();
        c0.setAccessible(true);
        SingleByEnum po = (SingleByEnum) c0.newInstance();
        System.out.println("无参构造函数\t" + po);
        System.out.println(po == instance);
    }
}