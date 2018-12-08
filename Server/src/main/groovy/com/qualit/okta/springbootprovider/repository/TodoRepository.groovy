package com.qualit.okta.springbootprovider.repository

import com.qualit.okta.springbootprovider.domain.Todo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource
interface TodoRepository extends JpaRepository<Todo, Long> {

}