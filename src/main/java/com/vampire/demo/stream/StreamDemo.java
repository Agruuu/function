package com.vampire.demo.stream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import	java.util.stream.Stream;

/**
 * Stream API 
 */
public class StreamDemo {

    static void gen1() {
        String[] arr = {"a", "b", "c", "1", "2", "3"};
        Stream<String> stream = Stream.of(arr);
    }

    static void gen2() {
        List<String> list = Arrays.asList("a", "b", "c", "1", "2", "3");
        Stream<String> stream = list.stream();
    }

    static void gen3() {
        Stream<Integer> stream = Stream.generate( () -> 1);
        stream.limit(3).forEach(System.out::println);
    }

    static void gen4() {
        Stream<Integer> stream = Stream.iterate(1, x -> x + 1);
        stream.limit(5).forEach(System.out::println);
    }

    static void gen5() {
        String str = "abcd12345";
        IntStream stream = str.chars();

        stream.forEach(x -> System.out.println(x));
    }

    static void gen6 () throws IOException {

        Stream<String> stream = Files.lines(Paths.get("/Users/agaru/study/1. Java/5. SegmentFault/2. Code/function/src/main/resources/lambda.md"));
        stream.forEach(System.out::println);

    }

    static void gen7() {
//        Arrays.asList(1, 2, 3, 4, 5).stream().filter(x -> {
//            System.out.println("222");
//            return x%2 == 0;
//        }).forEach(System.out::println);

//        Arrays.asList(1, 2, 3, 4, 5).stream().filter(x -> x % 2 == 0).forEach(System.out::println);
        int sum = Arrays.asList(1, 2, 3, 4, 5, 6).stream().filter(x -> x % 2 == 0).mapToInt(x -> x).sum();
        System.out.println(sum);

        int max = Arrays.asList(1, 2, 3, 4, 7, 5, 6).stream().max((a, b) -> a - b).get();
        System.out.println(max);

        int min =  Arrays.asList(1, 2, 3, 4, 5, 6).stream().min((a, b) -> a - b).get();
        System.out.println(min);
    }

    static void gen8() {
//        Optional<Integer> op1 = Arrays.asList(8, 9, 7, 1, 2, 3, 4, 5, 6).stream().findAny();
//        System.out.println(op1.get());
//
//        Optional<Integer> op2 = Arrays.asList(8, 9, 7, 1, 2, 3, 4, 5, 6).stream().sorted((a, b) -> b - a).findFirst();
//        System.out.println(op2.get());

//        Arrays.asList(12, 15, 9, 8, 16, 2).stream().sorted().forEach(System.out::println);
//        Arrays.asList(12, 15, 9, 8, 16, 2).stream().sorted((a, b) -> b - a).forEach(System.out::println);
//        Arrays.asList("cn", "admin", "web", "java").stream().sorted((a, b) -> a.length() - b.length()).forEach(System.out::println);

//        List<Integer> list = Stream.iterate(1, x -> x + 1).limit(20).filter(x -> x % 2 == 0).collect(Collectors.toList());
//        list.forEach(System.out::println);

//        Stream.iterate(1, x -> x + 1).limit(50).sorted((a, b) -> b - a).skip(30).limit(10).forEach(System.out::println);

//        String str = "11,22,33,44,55";
//        Integer sum = Stream.of(str.split(",")).map(x -> Integer.valueOf(x)).mapToInt(x -> x).sum();
//        System.out.println(sum);
//
//        String str1 = "11,22,33,44,55";
//        Integer sum1 = Stream.of(str1.split(",")).map(Integer::valueOf).mapToInt(x -> x).sum();
//        System.out.println(sum1);

//        String str = "tomcat,nginx,apache,jetty";
//        Stream.of(str.split(",")).map(User::new).forEach(System.out::println);
//        Stream.of(str.split(",")).map(x -> Person.build(x)).forEach(System.out::println);
//        Stream.of(str.split(",")).map(Person:: build).forEach(System.out::println);

//        String str = "11,22,33,44,55";
//        int sum = Stream.of(str.split(",")).peek(System.out::println).mapToInt(x -> Integer.valueOf(x)).sum();
//        System.out.println(sum);
    }


    public static void main(String[] args) throws IOException {
        StreamDemo.gen9();
    }

    /**
     * 并行流
     */
    private static void gen9() {
//        ForkJoinPool.commonPool().getParallelism()
//        java.util.concurrent.ForkJoinPool.common.parallelism
//        -Djava.util.concurrent.ForkJoinPool.common.parallelism
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "5");
        Optional<Integer> max = Stream.iterate(1, x -> x + 1).limit(200).peek(x -> {
            System.out.println(Thread.currentThread().getName());
        }).sequential().parallel().max(Integer::compare);
        System.out.println(max);
    }
}

class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}

class Person {
    private String name;

    public static Person build(String name) {
        Person p = new Person();
        p.name = name;
        return p;
    }

//    public Person(String name) {
//        this.name = name;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}
