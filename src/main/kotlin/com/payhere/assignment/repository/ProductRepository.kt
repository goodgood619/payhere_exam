package com.payhere.assignment.repository

import com.payhere.assignment.domain.entity.Product
import com.payhere.assignment.domain.entity.QProduct.product
import com.payhere.assignment.util.getChosungPattern
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<Product, String>, ProductRepositoryCustom {

    fun findById(id: Long): Product?
    fun deleteById(id: Long)
}

interface ProductRepositoryCustom {
    fun findAllByCondition(productId: Long?, name: String, pageSize: Long): List<Product>
}

class ProductRepositoryImpl(
    private val jpaQueryFactory: JPAQueryFactory,
) : ProductRepositoryCustom {
    override fun findAllByCondition(productId: Long?, name: String, pageSize: Long): List<Product> {

        return jpaQueryFactory.selectFrom(product)
            .where(
                ltProductId(productId),
                product.name.like("%$name%").or(product.nameChosung.like("%$name%")))
            .orderBy(product.id.desc())
            .limit(pageSize)
            .fetch()
    }

    private fun ltProductId(productId: Long?): BooleanExpression? {
        productId ?: return null
        return product.id.lt(productId)
    }

}