package com.org.common

import org.hyperledger.fabric.gateway.Wallet
import org.hyperledger.fabric.gateway.Wallets
import org.hyperledger.fabric.sdk.security.CryptoSuiteFactory
import org.hyperledger.fabric_ca.sdk.HFCAClient
import java.nio.file.Paths
import java.util.*

class HyperledgerObjectFactory(private val orgConfig: HyperledgerOrgConfig) {
    fun createCertificateAuthorityClient(): HFCAClient {
        val props = Properties()
        props["pemFile"] = orgConfig.pemFilePath
        props["allowAllHostNames"] = "true"
        val caClient = HFCAClient.createNewInstance(orgConfig.certificateAuthorityUrl, props)
        val cryptoSuite = CryptoSuiteFactory.getDefault().cryptoSuite
        caClient.cryptoSuite = cryptoSuite
        return caClient
    }

    fun createFileWallet(): Wallet {
        // Create a wallet for managing identities
        return Wallets.newFileSystemWallet(Paths.get(orgConfig.walletPath))
    }
}