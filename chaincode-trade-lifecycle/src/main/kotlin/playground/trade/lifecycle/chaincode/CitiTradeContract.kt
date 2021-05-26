package playground.trade.lifecycle.chaincode

import cdm.event.common.Trade
import org.hyperledger.fabric.contract.Context
import org.hyperledger.fabric.contract.ContractInterface
import org.hyperledger.fabric.contract.annotation.*

@Contract(name = "MyAssetContract",
    info = Info(title = "MyAsset contract",
        description = "Kotlin gradle dsl and Kotlin Contract",
        version = "0.0.1",
        license =
        License(name = "Apache-2.0", url = ""),
        contact = Contact(email = "trade.lifecycle@example.com",
            name = "Trade Life Cycle",
            url = "http://gradle-kotlin.me"))
)
class CitiTradeContract: ContractInterface {
    @Transaction(intent = Transaction.TYPE.EVALUATE)
    fun executeTrade(context: Context, instruction: String) {
        println("instruction received: $instruction")
    }
}