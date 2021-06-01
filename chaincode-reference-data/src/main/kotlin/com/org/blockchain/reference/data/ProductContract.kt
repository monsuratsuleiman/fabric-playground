package com.org.blockchain.reference.data

import com.fasterxml.jackson.databind.ObjectMapper
import org.hyperledger.fabric.contract.Context
import org.hyperledger.fabric.contract.ContractInterface
import org.hyperledger.fabric.contract.annotation.*


object JSON {
    val mapper = ObjectMapper()

    fun stringify(objectToStringify: Any): String {
        return mapper.writeValueAsString(objectToStringify)
    }
}
@Contract(name = "com.org.blockchain.reference.data",
    info = Info(title = "Security Contract",
        description = "Kotlin gradle dsl and Kotlin Contract",
        version = "0.0.1",
        license =
        License(name = "Apache-2.0", url = ""),
        contact = Contact(email = "reference.data@example.com",
            name = "The Owner",
            url = "http://gradle-kotlin.me")
    )
)
class ProductContract: ContractInterface {

    @Transaction(intent = Transaction.TYPE.SUBMIT)
    fun initialize(context: Context): Unit {
        createProduct(context, "smc1", ".FTSE")
        createProduct(context, "smc2", "IBM.N")
        createProduct(context, "smc3", "VOD.L")
        createProduct(context, "smc4", ".N225")
    }

    @Transaction(intent = Transaction.TYPE.SUBMIT)
    fun createProduct(ctx: Context, id: String, name: String): Product {
        val product = Product(id, name)
        val productJSON: String = JSON.stringify(product)
        ctx.stub.putStringState(id, productJSON)
        return product
    }
}