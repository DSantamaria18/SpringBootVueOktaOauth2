package com.qualit.okta.springbootprovider

import com.qualit.okta.springbootprovider.domain.Todo
import com.qualit.okta.springbootprovider.repository.TodoRepository
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.core.Ordered
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter
import java.util.Collections
import java.util.stream.Stream

@SpringBootApplication
class SpringBootProviderApplication {

    static void main(String[] args) {
        SpringApplication.run SpringBootProviderApplication, args
    }

    // Bootstrap some test data into the in-memory database
    @Bean
    ApplicationRunner init(TodoRepository repository) {

        return { args ->
            Stream.of("Buy milk", "Eat pizza", "Write tutorial", "Study Vue.js", "Go kayaking").forEach({ name ->
                Todo todo = new Todo()
                todo.setTitle(name)
                repository.save(todo)
            })
            repository.findAll().forEach({println(it.getName())})
        }
    }

    // Fix the CORS errors
    @Bean
    FilterRegistrationBean simpleCorsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource()
        CorsConfiguration config = new CorsConfiguration()
        config.setAllowCredentials(true)
        // *** URL below needs to match the Vue client URL and port ***
        config.setAllowedOrigins(Collections.singletonList("http://localhost:8080"))
        config.setAllowedMethods(Collections.singletonList("*"))
        config.setAllowedHeaders(Collections.singletonList("*"))
        source.registerCorsConfiguration("/**", config)
        FilterRegistrationBean bean = new FilterRegistrationBean<>(new CorsFilter(source))
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE)
        return bean
    }
}