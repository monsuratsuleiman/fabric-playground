package com.org.blockchain.trade.lifecycle.common

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.inject.Guice
import com.google.inject.Injector
import com.regnosys.rosetta.common.serialisation.RosettaObjectMapper
import org.hyperledger.fabric.contract.annotation.Serializer
import org.hyperledger.fabric.contract.execution.SerializerInterface
import org.hyperledger.fabric.contract.metadata.TypeSchema
import org.hyperledger.fabric.contract.routing.TypeRegistry
import org.isda.cdm.CdmRuntimeModule


object IsdaCdmModule {
    val injector: Injector = Guice.createInjector(CdmRuntimeModule())
    val objectMapper: ObjectMapper = RosettaObjectMapper.getNewRosettaObjectMapper()
}

@Serializer
class MySerializer: SerializerInterface {

    private val typeRegistry = TypeRegistry.getRegistry()
    override fun toBuffer(value: Any?, ts: TypeSchema?): ByteArray? {
        return ts?.let {
            IsdaCdmModule.objectMapper.writeValueAsBytes(value)
        }
    }

    override fun fromBuffer(buffer: ByteArray, ts: TypeSchema?): Any? {
        return ts?.let {
            val typeClass = ts.getTypeClass(typeRegistry)
            IsdaCdmModule.objectMapper.readValue(buffer, typeClass)
        }
    }
}