package com.org.blockchain.trade.lifecycle.trade

import cdm.event.common.ExecutionInstruction
import cdm.event.common.Trade
import com.org.blockchain.trade.lifecycle.common.IsdaCdmModule
import org.hyperledger.fabric.contract.Context
import org.hyperledger.fabric.contract.ContractInterface
import org.hyperledger.fabric.contract.annotation.*
import org.hyperledger.fabric.shim.ChaincodeStub

@Contract(name = "com.org.trade.lifecycle.trade",
    info = Info(title = "MyAsset contract",
        description = "Kotlin gradle dsl and Kotlin Contract",
        version = "0.0.1",
        license =
        License(name = "Apache-2.0", url = ""),
        contact = Contact(email = "trade.lifecycle@example.com",
            name = "The Owner",
            url = "http://gradle-kotlin.me"))
)
class MyTradeContract: ContractInterface {
    override fun createContext(stub: ChaincodeStub): Context {
        return MyTradeContext(stub)
    }

    @Transaction(intent = Transaction.TYPE.SUBMIT)
    fun executeTrade(context: Context, isdaCdmExecuteInstruction: String): MyTrade {
        ///val instruction = IsdaCdmModule.objectMapper.readValue(isdaCdmExecuteInstruction, ExecutionInstruction::class.java)
        println("instruction received: ooooo")
        return MyTrade(Trade.builder().build())
    }
}