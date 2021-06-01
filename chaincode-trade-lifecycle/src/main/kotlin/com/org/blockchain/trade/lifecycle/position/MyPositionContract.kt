package com.org.blockchain.trade.lifecycle.position

import cdm.event.common.ExecutionInstruction
import cdm.event.common.Trade
import org.hyperledger.fabric.contract.Context
import org.hyperledger.fabric.contract.ContractInterface
import org.hyperledger.fabric.contract.annotation.*
import org.hyperledger.fabric.shim.ChaincodeStub

@Contract(name = "com.org.trade.lifecycle.position",
    info = Info(title = "MyAsset contract",
        description = "Kotlin gradle dsl and Kotlin Contract",
        version = "0.0.1",
        license =
        License(name = "Apache-2.0", url = ""),
        contact = Contact(email = "trade.lifecycle@example.com",
            name = "The Owner",
            url = "http://gradle-kotlin.me"))
)
class MyPositionContract: ContractInterface {
    override fun createContext(stub: ChaincodeStub): Context {
        return MyPositionContext(stub)
    }

    @Transaction(intent = Transaction.TYPE.SUBMIT)
    fun executeTrade(context: Context, instruction: String): MyPosition {
        println("instruction received: $instruction")

        return MyPosition("xxxx", "1234")
    }
}