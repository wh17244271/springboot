package wh.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PersonData {
    private String id;
    private String type;
    private String name;
    private int age;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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

    public PersonData(String id, String type, String name, int age) {
        super();
        this.id = id;
        this.type = type;
        this.name = name;
        this.age = age;
    }

    public PersonData() {

    }

    @Override
    public String toString() {
        return "PersonData{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static void main(String[] args){
        List<PersonData> list = new ArrayList<PersonData>();
        PersonData p1 = new PersonData();
        p1.setId("1");
        p1.setName("张三");
        p1.setType("管理员");
        p1.setAge(20);
        list.add(p1);

        PersonData p2 = new PersonData();
        p2.setId("2");
        p2.setName("李四");
        p2.setType("管理员");
        p2.setAge(30);
        list.add(p2);

        PersonData p3 = new PersonData();
        p3.setId("3");
        p3.setName("王五");
        p3.setType("用户");
        p3.setAge(40);
        list.add(p3);

        PersonData p4 = new PersonData();
        p4.setId("4");
        p4.setName("马六");
        p4.setType("访客");
        p4.setAge(50);
        list.add(p4);


        //跟据某个属性分组
        Map<String, List<PersonData>> collect = list.stream().collect(Collectors.groupingBy(PersonData::getType));
        System.out.println(collect);

        //根据某个属性分组，汇总某个属性
        Map<String, Integer> collect2 = list.stream().collect(Collectors.groupingBy(PersonData::getType,Collectors.summingInt(PersonData::getAge)));
        System.out.println(collect2);

        //根据某个属性添加条件过滤数据，
        list = list.stream().filter(u -> !u.getType().equals("访客")).collect(Collectors.toList());
        System.out.println(list);

        //判断一组对象里面有没有属性值是某个值
        boolean add = list.stream().anyMatch(m -> "王五".equals(m.getName()));
        System.out.println(add);

        //取出一组对象的某个属性组成一个新集合
        List<String> names=list.stream().map(PersonData::getName).collect(Collectors.toList());
        System.out.println(names);
    }












}
