package com.org.blockchain.trade.lifecycle.position

import cdm.event.common.Trade
import org.hyperledger.fabric.contract.annotation.DataType
import org.hyperledger.fabric.contract.annotation.Property

@DataType
class MyPosition(@Property() val id: String, @Property() value: String) {
}