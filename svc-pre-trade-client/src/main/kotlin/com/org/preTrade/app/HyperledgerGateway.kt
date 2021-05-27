package com.org.preTrade.app

import cdm.event.common.ExecutionInstruction
import com.rosetta.model.lib.records.DateImpl
import org.hyperledger.fabric.gateway.Gateway
import org.hyperledger.fabric.gateway.Network
import org.hyperledger.fabric.gateway.Wallet
import org.hyperledger.fabric_ca.sdk.HFCAClient
import java.nio.file.Path
import java.nio.file.Paths
import java.time.LocalDate

class HyperledgerGateway(private val wallet: Wallet, hfcaClient: HFCAClient) {
    val networkConfigPath: Path = Paths.get(
        "..",
        "..",
        "fabric-samples",
        "test-network",
        "organizations",
        "peerOrganizations",
        "org1.example.com",
        "connection-org1.yaml"
    )

    fun executeTrade(onBehalfOf: String, executeInstruction: ExecutionInstruction) {
        val builder = Gateway.createBuilder()
        builder.identity(wallet, onBehalfOf).networkConfig(networkConfigPath).discovery(true)
        builder.connect().use { gateway ->
            kotlin.runCatching {
                val network: Network = gateway.getNetwork("mychannel")
                val contract = network.getContract("chaincode-trade-lifecycle", "com.org.trade.lifecycle.trade")

                println("Submit Transaction: InitLedger creates the initial set of assets on the ledger.")

                val result = contract.submitTransaction("executeTrade", executeInstruction.toString())

                println("Result for submitting execute ${result}")
            }.exceptionOrNull()?.let {
                println("Error submitting execute ${it}")
            }
        }
    }

}