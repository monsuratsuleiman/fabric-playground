package com.org.preTrade.app

import org.hyperledger.fabric.gateway.Identities
import org.hyperledger.fabric.gateway.X509Identity
import org.hyperledger.fabric.sdk.Enrollment
import org.hyperledger.fabric.sdk.User
import java.security.PrivateKey


class HyperledgerEnrollment(private val adminIdentity: X509Identity) : Enrollment {
    override fun getKey(): PrivateKey {
        return adminIdentity.privateKey
    }

    override fun getCert(): String {
        return Identities.toPemString(adminIdentity.certificate);
    }

}

class HyperledgerUser(private val userName: String,
                      private val userAffiliation: String,
                      private val userMspId: String,
                      private val adminIdentity: X509Identity
): User {
    override fun getName(): String {
        return userName
    }

    override fun getRoles(): MutableSet<String>? {
        return null
    }

    override fun getAccount(): String? {
        return null
    }

    override fun getEnrollment(): Enrollment {
        return HyperledgerEnrollment(adminIdentity)
    }

    override fun getMspId(): String {
        return userMspId
    }

    override fun getAffiliation(): String {
        return userAffiliation
    }

}