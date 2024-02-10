package com.payhere.assignment.repository

import com.payhere.assignment.domain.entity.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<Product, String> {

    fun findById(id: Long): Product?
    fun deleteById(id: Long)
}