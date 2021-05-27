package com.org.blockchain.trade.lifecycle.trade

import com.google.inject.Guice
import org.hyperledger.fabric.contract.Context
import org.hyperledger.fabric.shim.ChaincodeStub
import org.isda.cdm.CdmRuntimeModule

class MyTradeContext(stub: ChaincodeStub): Context(stub) {
}