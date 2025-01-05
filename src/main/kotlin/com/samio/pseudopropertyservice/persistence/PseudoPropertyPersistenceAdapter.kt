package com.samio.pseudopropertyservice.persistence

import com.ecommercedemo.common.persistence.abstraction.EntityPersistenceAdapter
import com.ecommercedemo.common.persistence.annotation.PersistenceAdapterFor
import com.samio.pseudopropertyservice.model.PseudoProperty
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.stereotype.Service

@Service
@PersistenceAdapterFor(PseudoProperty::class)
class PseudoPropertyPersistenceAdapter : EntityPersistenceAdapter<PseudoProperty>()