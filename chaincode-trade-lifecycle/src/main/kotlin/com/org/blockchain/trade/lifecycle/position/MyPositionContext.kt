package com.org.blockchain.trade.lifecycle.position

import com.google.inject.Guice
import org.hyperledger.fabric.contract.Context
import org.hyperledger.fabric.shim.ChaincodeStub
import org.isda.cdm.CdmRuntimeModule

class MyPositionContext(stub: ChaincodeStub): Context(stub) {
    val injector = Guice.createInjector(CdmRuntimeModule())
}