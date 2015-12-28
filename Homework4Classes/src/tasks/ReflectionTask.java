/**
 * 
 */
package tasks;

import java.lang.reflect.Field;

import model.Person;

/**
 * @author pzoli
 * http://tutorials.jenkov.com/java-reflection/
 * 
 */
public class ReflectionTask {

    public static void getReflection() {
        Person p = new Person();
        p.setId(1);
        p.setName("Toka Moto");
        p.setAge((byte) 45);
        System.out.println(p);
        Field[] fields = Person.class.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getType().getName() + ": " + field.getName());
            if (field.getType().isPrimitive()) {
                field.setAccessible(true);
                try {
                    switch (field.getType().getName()) {
                    case "byte":
                        field.set(p, (byte) 5);
                        break;
                    case "int":
                        field.set(p, 2);
                        break;
                    }
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                System.out.println(p);
            } else {
                try {
                    field.setAccessible(true);
                    field.set(p, "Motorola");
                    System.out.println(p);
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
