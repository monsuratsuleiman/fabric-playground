package com.org.blockchain.trade.lifecycle.trade

import cdm.event.common.Trade
import com.rosetta.model.metafields.FieldWithMetaDate
import org.hyperledger.fabric.contract.annotation.DataType
import org.hyperledger.fabric.contract.annotation.Property

@DataType
class Trade(private val tradeInstance: Trade): Trade by tradeInstance {
    constructor(@Property()tradeDate: FieldWithMetaDate): this(Trade.builder().setTradeDate(tradeDate))
}