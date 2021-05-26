package playground.trade.lifecycle.chaincode

import cdm.event.common.Trade
import org.hyperledger.fabric.contract.annotation.DataType
import org.hyperledger.fabric.contract.annotation.Property

@DataType
class CitiTrade(@Property() val id: String, @Property() value: String) {
}