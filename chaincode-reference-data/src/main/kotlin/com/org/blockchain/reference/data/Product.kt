package com.org.blockchain.reference.data

import org.hyperledger.fabric.contract.annotation.DataType
import org.hyperledger.fabric.contract.annotation.Property

@DataType
class Product(@Property() val productId: String, @Property() val name: String)