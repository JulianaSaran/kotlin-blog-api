package com.julianasaran.blog.domain.exceptions

class EntityNotFound(entity: String) : Exception("$entity not found")
