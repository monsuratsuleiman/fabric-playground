package com.org.preTrade.app

import org.hyperledger.fabric.gateway.Wallet
import org.hyperledger.fabric.gateway.Wallets
import org.hyperledger.fabric.sdk.security.CryptoSuiteFactory
import org.hyperledger.fabric_ca.sdk.HFCAClient
import java.nio.file.Paths
import java.util.*

class HyperledgerObjectFactory {
    fun createCertificateAuthorityClient(): HFCAClient {
        val props = Properties()
        props["pemFile"] =
            "../../fabric-samples/test-network/organizations/peerOrganizations/org1.example.com/ca/ca.org1.example.com-cert.pem"
        props["allowAllHostNames"] = "true"
        val caClient = HFCAClient.createNewInstance("https://localhost:7054", props)
        val cryptoSuite = CryptoSuiteFactory.getDefault().cryptoSuite
        caClient.cryptoSuite = cryptoSuite
        return caClient
    }

    fun createFileWallet(): Wallet {
        // Create a wallet for managing identities
        return Wallets.newFileSystemWallet(Paths.get("wallet"))
    }
}