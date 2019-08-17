package com.vampire.demo.method;
import	java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import	java.util.function.Supplier;

/**
 * 构造方法引用
 */
public class ConstructorReference {

    public static void main(String[] args) {

        Supplier<Person> s1 = () -> new Person();
        s1.get();

        Supplier<Person> s2 = Person::new;
        s2.get();

        Supplier<List> s3 = ArrayList::new;

        Supplier<Thread> s4 = Thread::new;

        Supplier<Set> s5 = HashSet::new;

        Supplier<String> s6 = String::new;

        Consumer<Integer> c1 = (age) -> new Human(age);
        c1.accept(18);

        Consumer<Integer> c2 = Human::new;
        c2.accept(25);

        Function<String, Integer> f1 = (str) -> Integer.valueOf(str);
        Function<String, Integer> f2 = Integer::valueOf;

        Function<String, Person> f3 = (str) -> new Person();
        Function<String, Person> f4 = Person::new;
    }


}

class Person {
    private String name;

    public Person() {
        System.out.println("person");
    }

    public Person(String name) {

    }
}

class Human {

    public Human(Integer age) {
        System.out.println(age);
    }
}
