package com.fxf.springCloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author FXF
 * @create 2020-05-20 17:26
 */
@Component
public class myRoutes {

    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
        RouteLocator route=builder.routes()
                .route(r -> r.path("/customer/**")
                        .filters(f -> f.filter(new myfilter())
                                .addResponseHeader("X-Response-Default-Foo", "Default-Bar")
                                .stripPrefix(1) //路由时转发时，去掉第一个前缀。即customer
                        )
                        .uri("lb://cloud-nacos-consumer")
                        .order(0)
                        .id("customer_filter_router")
                ).build();

        return route;

    }

}
