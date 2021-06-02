package com.org.preTrade.app

import cdm.event.common.ExecutionInstruction
import com.org.common.HyperledgerOrgConfig
import org.hyperledger.fabric.gateway.Gateway
import org.hyperledger.fabric.gateway.Network
import org.hyperledger.fabric.gateway.Wallet
import org.hyperledger.fabric.sdk.Peer
import org.hyperledger.fabric_ca.sdk.HFCAClient
import java.nio.file.Path
import java.nio.file.Paths

class HyperledgerGateway(private val wallet: Wallet, private val orgConfig: HyperledgerOrgConfig) {


    fun executeTrade(onBehalfOf: String, executeInstruction: ExecutionInstruction) {
        val builder = Gateway.createBuilder()
        builder.identity(wallet, onBehalfOf).networkConfig(orgConfig.networkConfigPath).discovery(true)
        builder.connect().use { gateway ->
            kotlin.runCatching {
                val network: Network = gateway.getNetwork("mychannel")
                val contract = network.getContract("chaincode-trade-lifecycle", "com.org.trade.lifecycle.trade")

                val result = contract.submitTransaction("executeTrade", executeInstruction.toString())

                println("Result for submitting execute ${result}")
            }.exceptionOrNull()?.let {
                println("Error submitting execute ${it}")
            }
        }
    }

}