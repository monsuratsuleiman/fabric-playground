package com.org.reference.data

import com.org.common.*
import org.hyperledger.fabric.gateway.Gateway
import org.hyperledger.fabric.gateway.Network
import org.hyperledger.fabric.gateway.Wallet
import java.util.concurrent.CountDownLatch
import kotlin.concurrent.thread


class ReferenceDataApp {

    private val orgConfig = Org3Config

    private val hyperledgerObjectFactory = HyperledgerObjectFactory(orgConfig)
    private val wallet = hyperledgerObjectFactory.createFileWallet()
    private val hfcaClient = hyperledgerObjectFactory.createCertificateAuthorityClient()
    private val registerer = UserRegisterer(wallet, hfcaClient, hyperledgerObjectFactory, orgConfig)
    private val gateway = ReferenceDataHyperledgerGateway(wallet, orgConfig)
    private val countDownLatch = CountDownLatch(1)

    fun start() {
        gateway.initialize(registerer.anyAppUser)
    }

    fun stop(){
        countDownLatch.countDown()
    }

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            ReferenceDataApp().start()
        }
    }
}

class ReferenceDataHyperledgerGateway(private val wallet: Wallet, private val orgConfig: HyperledgerOrgConfig) {
    fun initialize(onBehalfOf: String) {
        val builder = Gateway.createBuilder()
        builder.identity(wallet, onBehalfOf).networkConfig(orgConfig.networkConfigPath).discovery(true)
        builder.connect().use { gateway ->
            kotlin.runCatching {
                val network: Network = gateway.getNetwork("mychannel")
                val contract = network.getContract("chaincode-reference-data", "com.org.blockchain.reference.data")

                val result = contract.submitTransaction("initialize")

                println("Result for submitting execute $result")

            }.exceptionOrNull()?.let {
                println("Error submitting execute $it")
            }
        }
    }
}