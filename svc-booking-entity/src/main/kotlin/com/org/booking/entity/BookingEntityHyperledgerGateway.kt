package com.org.booking.entity

import com.org.common.HyperledgerOrgConfig
import org.hyperledger.fabric.gateway.Gateway
import org.hyperledger.fabric.gateway.Network
import org.hyperledger.fabric.gateway.Wallet
import java.nio.file.Paths

class BookingEntityHyperledgerGateway(private val wallet: Wallet, private val config: HyperledgerOrgConfig) {
    fun watchTradeBusinessEvents(onBehalfOf: String) {
        val builder = Gateway.createBuilder()
        builder.identity(wallet, onBehalfOf).networkConfig(config.networkConfigPath).discovery(true)
        builder.connect().let { gateway ->
            kotlin.runCatching {
                val network: Network = gateway.getNetwork("mychannel")
                val contract = network.getContract("chaincode-trade-lifecycle", "com.org.trade.lifecycle.trade")
                contract.addContractListener(org.hyperledger.fabric.gateway.DefaultCheckpointers.file(Paths.get("checkpointer"))){
                    println("******************************** Booking Entity Received event ${it.name}, potential source ${it.transactionEvent.peer.name}")
                }
            }.exceptionOrNull()?.let {
                println("Error watching event $it")
            }
        }
    }
}