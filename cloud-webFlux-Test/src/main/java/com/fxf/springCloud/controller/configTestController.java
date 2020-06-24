package com.fxf.springCloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * @author FXF
 * @create 2020-05-15 13:32
 */
@RestController
@Slf4j
public class configTestController {

    // 阻塞5秒钟
    private String createStr() {
        try {
            TimeUnit.SECONDS.sleep(10);
            log.info("拆包裹");
        } catch (InterruptedException e) {
        }
        return "快递到了";
    }


    // 普通的SpringMVC方法
    @GetMapping("/1")
    private String get1() {
        log.info("等快递");
        String result = createStr();
        log.info(result);
        log.info("拆包裹");
        log.info("看电视");
        log.info(result);

        return "完成";
    }

    // WebFlux(返回的是Mono)
    @GetMapping("/2")
    private Mono<String> get2() {
        log.info("等快递");
        Mono<String> result = Mono.fromSupplier(() -> createStr());
       /* result.map(item->{
                System.out.println(item);
                System.out.println(item);

                log.info(item);
                log.info("拆包裹");
            return Mono.just(item);
        });*/


        log.info("看电视");

        return result;
    }

    public static void main(String[] args) {
        Function<Integer,Integer> test= i->i+1;
        Integer s=test.apply(5); //执行i+1操作，5作为参数 Function<入参类型,回参类型>


        Function<Integer,Integer> A=i->i+1;
        Function<Integer,Integer> B=i->i*i;
        System.out.println("F1:"+B.apply(A.apply(5)));
        System.out.println("F2:"+A.apply(B.apply(5)));

        //Function<Integer,Integer> A=i->i+1; 相当于y=f(x);  x就是i,函数体的入参，f()相当于i+1 函数体  A就相当于Y表示这个函数
        //A.apply(5); 相当于f(5); 执行该函数返回f(5)函数体的执行结果


        //compose 含义，将函数体作为参数传入,该函数体入参？是继承V的, 这个(V v) 又作为新的入参 相当于f(x)中的X,也就是传入的函数体的入参，作为新函数体的入参
        //类似于 y=f1(f(x)); 先执行f(x),在执行f1(x)

        /*default <V> Function<V, R> compose(Function<? super V, ? extends T> before) {
            Objects.requireNonNull(before);
            return (V v) -> apply(before.apply(v));
        }*/

        //这个理解就是f1(f(5))    (5)->{B.apply(A.apply(5))}
        System.out.println("F1:"+B.compose(A).apply(5));

       /* default <V> Function<T, V> andThen(Function<? super R, ? extends V> after) {
            Objects.requireNonNull(after);
            return (T t) -> after.apply(apply(t));
        }*/

        //f(f1(5))  (5)->{A.apply(B.apply(5))}
        System.out.println("F2:"+B.andThen(A).apply(5));

        //5+1;6+1;7*7;49+1;return 50;
        //B.compose(A1).compose(A2).andThen(A3).apply(5)
        //函数体A1作为函数B的入参，函数体A2作为函数体A1的入参，函数B作为函数体A3的入参 A3(B(A1(A2())))
        System.out.println("FN:"+B.compose(A).compose(A).andThen(A).apply(5));
    }




}
