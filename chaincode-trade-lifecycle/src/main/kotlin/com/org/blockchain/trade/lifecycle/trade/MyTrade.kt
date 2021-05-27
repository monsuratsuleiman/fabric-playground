package com.org.blockchain.trade.lifecycle.trade

import cdm.base.staticdata.identifier.Identifier
import cdm.base.staticdata.party.Account
import cdm.base.staticdata.party.Party
import cdm.base.staticdata.party.PartyRole
import cdm.event.common.ContractDetails
import cdm.event.common.ExecutionDetails
import cdm.event.common.Trade
import cdm.legalagreement.csa.Collateral
import cdm.product.common.settlement.SettlementTerms
import cdm.product.template.TradableProduct
import com.rosetta.model.lib.records.Date
import com.rosetta.model.metafields.FieldWithMetaDate
import com.rosetta.model.metafields.MetaFields
import org.hyperledger.fabric.contract.annotation.DataType
import org.hyperledger.fabric.contract.annotation.Property

@DataType
class MyTrade(private val tradeInstance: Trade): Trade by tradeInstance {
    constructor(@Property()tradeDate: FieldWithMetaDate): this(Trade.builder().setTradeDate(tradeDate))
}