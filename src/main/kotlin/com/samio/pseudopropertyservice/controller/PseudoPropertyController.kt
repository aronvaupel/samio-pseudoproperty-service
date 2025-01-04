package com.samio.pseudopropertyservice.controller

import com.ecommercedemo.common.controller.abstraction.RestControllerTemplate
import com.ecommercedemo.common.controller.annotation.ControllerFor
import com.samio.pseudopropertyservice.model.PseudoProperty
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/pseudo-properties")
@ControllerFor(PseudoProperty::class)
@ConditionalOnClass(name = ["org.springframework.data.jpa.repository.JpaRepository"])
class PseudoPropertyController: RestControllerTemplate<PseudoProperty>()