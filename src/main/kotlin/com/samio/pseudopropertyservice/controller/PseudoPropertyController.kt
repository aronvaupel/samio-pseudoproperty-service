package com.samio.pseudopropertyservice.controller

import com.ecommercedemo.common.controller.abstraction.RestControllerTemplate
import com.ecommercedemo.common.controller.annotation.ControllerFor
import com.samio.pseudopropertyservice.model.PseudoProperty
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/pseudo-properties")
@ControllerFor(PseudoProperty::class)
class PseudoPropertyController: RestControllerTemplate<PseudoProperty>()