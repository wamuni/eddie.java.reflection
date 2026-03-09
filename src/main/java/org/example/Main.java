package org.example;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
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

        // 可以通过名字来找相应的feild
        Field fieldByName = userClazz.getDeclaredField("name");
        System.out.println(fieldByName.getType());

        // 对于有泛型类型声明的字段field
        // 如果name传错了，那么会报错，NoSuchFieldException
        Field fieldByGeneric = userClass.getDeclaredField("comments");
        // 只获取类型会只返回相应的类型，会进行泛型擦除
        System.out.println(fieldByGeneric.getType());
        System.out.println(fieldByGeneric.getGenericType());

        // 通过反射来获取静态field和方法
        Class<?> staticClazz = Class.forName("org.example.User");
        Field staticField = staticClazz.getDeclaredField("publicStaticField");
        // 因为获取的静态字段值，所以不需要一个具体的实例，因此可以通过null object来获取
        // 但是需要处理异常
        System.out.println(staticField.get(null));
        staticField = staticClazz.getDeclaredField("privateStaticField");
        // 在获取似有静态字段时，因为时私有的，所以需要获取权限
        staticField.setAccessible(true);
        // 可以set 新的值
        staticField.set(null, 100);
        System.out.println(staticField.get(null));

        Method method = staticClazz.getDeclaredMethod("myPublicStaticMethod");
        method.invoke(null);

        // 可以根据参数类型来获取对应的方法
        Method staticMethod = staticClazz.getDeclaredMethod(
                "myPrivateStaticMethod",
                String.class
        );
        staticMethod.setAccessible(true);
        staticMethod.invoke(null, "Hello");

        // 利用反射构造一个类的实例
        // 默认方法没有参数，那么就是一个默认无参构造器
        Constructor<?> constructor = staticClazz.getDeclaredConstructor();
        // 因为实在运行时进行的，所以在编译阶段无法确定当前是哪个类，所以可以用obj接受
        Object obj = constructor.newInstance();
        // 之后可以来一个类型转换
        if (obj instanceof User) {
            User user1 = (User) obj;
        }
    }
}