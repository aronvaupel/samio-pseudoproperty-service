package com.samio.pseudopropertyservice.persistence

import com.ecommercedemo.common.persistence.abstraction.EntityRepository
import com.samio.pseudopropertyservice.model.PseudoProperty
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import java.util.*

@Suppress("unused")
@ConditionalOnClass(name = ["org.springframework.data.jpa.repository.JpaRepository"])
interface PseudoPropertyRepository : EntityRepository<PseudoProperty, UUID>

