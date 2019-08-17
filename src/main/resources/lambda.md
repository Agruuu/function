##lambda表达式
- Supplier 一个输出
- Consumer 一个输入
- BiConsumer 两个输入
- Function 一个输入一个输出（一般输入和输出是不同类型的）
- UnaryOperator 代表一个输入一个输出（输入和输出是相同类型的）
- BiFunction 代表两个输入一个输出（输入和输出是不同类型的）
- BinaryOperator 代表两个输入一个输出（输入和输出是相同类型的）

```java
/**
 * Lambda 表达式
 */
public class LambdaExample {

    public static void main(String[] args) {
        /**
         * 无参数无返回值
         */
        Runnable r1 = () -> {};
        /**
         * 无惨有返回值
         */
        Callable<Integer> r2 = () -> {return 1;};

        /**
         * 有参数无返回值
         */
        Consumer<String> c1 = (a) -> System.out.println(a);
        Consumer<String> c2 = a -> {};
        Consumer<String> c3 = (String a) -> {
            // 具体的业务逻辑
            System.out.println(a);
        };
        c1.accept("Hello World");

        /**
         * 无参数有返回值
         */
        Supplier<String> s1 = () -> "Hello World";
        Supplier<String> s2 = () -> {
            // 具体的业务逻辑
            return "Hello World";
        };

        /**
         * 有入参有返回值
         */
        Function<String, Integer> s3 = a -> a.length();
        Function<String, Integer> s4 = (String str) -> {
            // 具体的业务逻辑
            return Integer.valueOf(str);
        };
        
        Function<String, String> fn = str -> str.toUpperCase();
        System.out.println(fn.apply("function"));

        Consumer<String> cn = str -> System.out.println(str);
        cn.accept("print");
    }
}
```

