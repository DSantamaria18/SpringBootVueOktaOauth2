package com.qualit.okta.springbootprovider.repository

import com.qualit.okta.springbootprovider.domain.Todo
import org.springframework.data.rest.core.config.RepositoryRestConfiguration
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer
import org.springframework.stereotype.Component

@Component
class RestRepositoryConfigurator implements RepositoryRestConfigurer {

    @Override
    void configureRepositoryRestConfiguration(RepositoryRestConfiguration config){
        config.exposeIdsFor(Todo.class)
    }
}
