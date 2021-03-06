/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package com.org.preTrade.app

import cdm.event.common.ExecutionInstruction
import com.google.inject.Guice
import com.google.inject.Injector
import com.org.common.HyperledgerObjectFactory
import com.org.common.Org1Config
import com.org.common.UserRegisterer
import com.rosetta.model.lib.records.DateImpl
import org.isda.cdm.CdmRuntimeModule
import java.time.LocalDate


class PreTradeApp {
    val injector: Injector = Guice.createInjector(CdmRuntimeModule())

    init {
        System.setProperty("org.hyperledger.fabric.sdk.service_discovery.as_localhost", "true")
    }


    companion object {

        @JvmStatic
        fun main(args: Array<String>) {

            val hyperledgerObjectFactory = HyperledgerObjectFactory(Org1Config)
            val wallet = hyperledgerObjectFactory.createFileWallet()
            val hfcaClient = hyperledgerObjectFactory.createCertificateAuthorityClient()
            val registerer = UserRegisterer(wallet, hfcaClient, hyperledgerObjectFactory, Org1Config)
            val gateway = HyperledgerGateway(wallet, Org1Config)

            gateway.executeTrade(registerer.anyAppUser, ExecutionInstruction.builder().setTradeDate(DateImpl(LocalDate.now())))
        }
    }
}

