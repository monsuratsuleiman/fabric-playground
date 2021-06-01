package com.org.booking.entity

import com.google.inject.Guice
import com.google.inject.Injector
import com.org.common.HyperledgerObjectFactory
import com.org.common.Org2Config
import com.org.common.UserRegisterer
import org.isda.cdm.CdmRuntimeModule
import java.util.concurrent.CountDownLatch
import kotlin.concurrent.thread


class BookingApp {
    val injector: Injector = Guice.createInjector(CdmRuntimeModule())

    private val hyperledgerObjectFactory = HyperledgerObjectFactory(Org2Config)
    private val wallet = hyperledgerObjectFactory.createFileWallet()
    private val hfcaClient = hyperledgerObjectFactory.createCertificateAuthorityClient()
    private val registerer = UserRegisterer(wallet, hfcaClient, hyperledgerObjectFactory, Org2Config)
    private val gateway = BookingEntityHyperledgerGateway(wallet, Org2Config)
    private val countDownLatch = CountDownLatch(1)

    init {
        System.setProperty("org.hyperledger.fabric.sdk.service_discovery.as_localhost", "true")
    }

    fun start() {
        thread(true) {
            gateway.watchTradeBusinessEvents(registerer.anyAppUser)
            countDownLatch.await()
        }
    }

    fun stop(){
        countDownLatch.countDown()
    }
}

