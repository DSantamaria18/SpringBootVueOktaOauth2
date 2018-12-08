package com.qualit.okta.springbootprovider.domain

import groovy.transform.Canonical

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.validation.constraints.NotNull

@Entity
@Canonical
class Todo {

    @Id
    @GeneratedValue
    private Long id

    @NotNull
    private String title

    private Boolean completed = false
}
