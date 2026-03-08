package org.example;

import java.lang.reflect.Field;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        int field = User.publicStaticField;
        System.out.println(field);
        User.myPublicStaticMethod();

        User user = new User("Eddie", 30);
        System.out.println(user.name);
        user.myPublicMethod();

        Class<User> userClass = User.class;
        // 这里使用通配符，因为类对象的类必须要在runtime时才能确定，在编译时确定不了
        Class<?> userClazz = user.getClass();

        Class<?> clazz = Class.forName("org.example.User");

        Field[] fields = clazz.getDeclaredFields();
        for (Field f: fields) {
            System.out.println(f.getName());
        }
    }
}