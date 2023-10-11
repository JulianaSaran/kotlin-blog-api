package com.julianasaran.blog.domain.exceptions

open class EntityNotFound(entity: String) : Exception("$entity not found")
