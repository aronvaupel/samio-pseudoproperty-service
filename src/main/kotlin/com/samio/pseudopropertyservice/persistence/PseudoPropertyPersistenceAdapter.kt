package com.samio.pseudopropertyservice.persistence

import com.ecommercedemo.common.persistence.abstraction.EntityPersistenceAdapter
import com.ecommercedemo.common.persistence.annotation.PersistenceAdapterFor
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.stereotype.Service

@Service
@PersistenceAdapterFor(com.samio.pseudopropertyservice.model.PseudoProperty::class)
@ConditionalOnClass(name = ["org.springframework.data.jpa.repository.JpaRepository"])
class PseudoPropertyPersistenceAdapter : EntityPersistenceAdapter<com.samio.pseudopropertyservice.model.PseudoProperty>()