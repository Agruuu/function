package com.vampire.demo.test;
import java.util.*;
import	java.io.PrintWriter;

import org.junit.Test;

import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LambdaTest {

    @Test
    public void test1() {
        String queryString = "itemId=1&userId=10000&type=20&token=123213&key=index";
        Map<String, String> map = Stream.of(queryString.split("&")).map(str -> str.split("=")).collect(Collectors.toMap(s -> s[0], s -> s[1]));
        System.out.println(map);
    }

    @Test
    public void test2() {
        List<String> names = books().stream().map(book -> book.getName()).collect(Collectors.toList());
        System.out.println(names);

        names = books().stream().map(Book::getName).collect(Collectors.toList());
        System.out.println(names);

        String str = books().stream().map(book -> book.getId() + "").collect(Collectors.joining(","));
        System.out.println(str);

        str = books().stream().map(book -> book.getId() + "").collect(Collectors.joining(",", "(", ")"));
        System.out.println(str);
    }

    @Test
    public void test3() {
        List<String> list = books().stream().map(book -> book.getType()).collect(Collectors.toList());
        System.out.println(list);

        list = books().stream().map(book -> book.getType()).distinct().collect(Collectors.toList());
        System.out.println(list);

        Set<String> set = books().stream().map(book -> book.getType()).collect(Collectors.toSet());
        System.out.println(set);
    }

    @Test
    public void test4() {
//        books().stream().sorted((book1, book2) -> Double.compare(book1.getPrice(), book2.getPrice())).forEach(System.out::println);
        Comparator<Book> comparator1 = (book1, book2) -> Double.compare(book2.getPrice(), book1.getPrice());
//        books().stream().sorted(comparator1.reversed().thenComparing((book1, book2) -> book1.getPublishDate().isAfter(book2.getPublishDate()) ? 1 : -1)).forEach(System.out::println);

        books().stream().sorted(Comparator.comparing(Book::getPrice).thenComparing(Comparator.comparing(Book::getPrice).reversed())).forEach(System.out::println);
    }

    @Test
    public void test5() {
        Map<Integer, Book> map = books().stream().collect(Collectors.toMap(book -> book.getId(), book -> book));
//        System.out.println(map);

        map = books().stream().collect(Collectors.toMap(Book::getId, book -> book));
        System.out.println(map);
    }

    @Test
    public void test6() {
        Double avg = books().stream().collect(Collectors.averagingDouble(Book::getPrice));
        System.out.println(avg);
    }

    @Test
    public void test7() {
        Optional<Book> book = books().stream().collect(Collectors.maxBy(Comparator.comparing(Book::getPrice)));
        System.out.println(book);

        book = books().stream().collect(Collectors.minBy(Comparator.comparing(Book::getPrice)));
        System.out.println(book);

        book = books().stream().collect(Collectors.maxBy(Comparator.comparing(Book::getPublishDate)));
        System.out.println(book);

        book = books().stream().collect(Collectors.minBy(Comparator.comparing(Book::getPublishDate)));
        System.out.println(book);
    }

    @Test
    public void test8() {
        Map<String, List<Book>> map = books().stream().collect(Collectors.groupingBy(Book::getType));
        map.keySet().forEach(key -> {
            System.out.println(key);
            System.out.println(map.get(key));
        });

        Map<String, Long> booksCount = books().stream().collect(Collectors.groupingBy(Book::getType, Collectors.counting()));
        System.out.println(booksCount);

        Map<String, Double> booksSum = books().stream().collect(Collectors.groupingBy(Book::getType, Collectors.summingDouble(Book::getPrice)));
        System.out.println(booksSum);

        Map<String, Double> booksAvg = books().stream().collect(Collectors.groupingBy(Book::getType, Collectors.averagingDouble(Book::getPrice)));
        System.out.println(booksAvg);

        Map<String, Optional<Book>> booksMax = books().stream().collect(Collectors.groupingBy(Book::getType, Collectors.maxBy(Comparator.comparing(Book::getPrice))));
        System.out.println(booksMax);

        Map<String, Optional<Book>> booksMin = books().stream().collect(Collectors.groupingBy(Book::getType, Collectors.minBy(Comparator.comparing(Book::getPrice))));
        System.out.println(booksMin);

        Map<String, Optional<Book>> booksAfter = books().stream().collect(Collectors.groupingBy(Book::getType, Collectors.minBy(Comparator.comparing(Book::getPublishDate).reversed())));
        System.out.println(booksAfter);
    }

    @Test
    public void test9() {
        Stream<Book> stream = books().stream().filter(book -> book.getPrice() >= 50).sorted(Comparator.comparing(Book::getPublishDate).reversed());
        stream.forEach(System.out::println);

    }

    private List<Book> books() {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1, "tomcat", 51d, "服务器", LocalDate.parse("2019-01-01")));
        books.add(new Book(2, "jetty", 51d, "服务器", LocalDate.parse("2019-05-22")));
        books.add(new Book(3, "nginx", 65.9d, "服务器", LocalDate.parse("2019-03-28")));
        books.add(new Book(4, "java", 65.9d, "编程语言", LocalDate.parse("2019-02-25")));
        books.add(new Book(5, "ruby", 34d, "编程语言", LocalDate.parse("2019-07-12")));
        books.add(new Book(6, "spring", 66d, "编程语言", LocalDate.parse("2019-08-02")));
        books.add(new Book(7, "html", 21d, "编程语言", LocalDate.parse("2019-04-25")));
        books.add(new Book(8, "database", 25d, "数据库", LocalDate.parse("2019-03-21")));
        books.add(new Book(9, "设计模式", 58d, "其他", LocalDate.parse("2019-02-11")));
        books.add(new Book(10, "重构", 22.2d, "其他", LocalDate.parse("2019-01-02")));
        return books;
    }
}
