package com.org.preTrade.app

import org.hyperledger.fabric.gateway.Identities
import org.hyperledger.fabric.gateway.Wallet
import org.hyperledger.fabric.gateway.X509Identity
import org.hyperledger.fabric_ca.sdk.EnrollmentRequest
import org.hyperledger.fabric_ca.sdk.HFCAClient
import org.hyperledger.fabric_ca.sdk.RegistrationRequest

class UserRegisterer(private val wallet: Wallet, private val hfcaClient: HFCAClient) {
    private val adminUser = "admin"
    private val appUsers = (0..0).map { "appUser_${it}" }
    private val affiliation = "org1.department1"
    private val mspId = "Org1MSP"
    private val admin: HyperledgerUser
    private val registeredAppUsers: List<HyperledgerUser>

    init {
        val identity = registerAdminUser()
        admin = HyperledgerUser(adminUser, affiliation, mspId, identity)
        registeredAppUsers = appUsers.map { HyperledgerUser(it, affiliation, mspId, registerAppUser(it)) }
    }

    val anyAppUser: String
        get() = appUsers.first()

    private fun registerAppUser(userName: String): X509Identity {

        val userIdentity = wallet[userName] as? X509Identity

        if(userIdentity != null) return userIdentity

        val result = kotlin.runCatching {
            val registrationRequest = RegistrationRequest(userName, affiliation)
            registrationRequest.type = "client"
            val enrollmentSecret = hfcaClient.register(registrationRequest, admin)
            println("======================> Successfully registered user${enrollmentSecret}")

            val enrollment = HyperledgerObjectFactory().createCertificateAuthorityClient().enroll(userName, enrollmentSecret)
//            val enrollment = hfcaClient.enroll(userName, enrollmentSecret, enrollmentRequestTLS)
//            val enrollment = hfcaClient.enroll(userName, "dvYShgmDkKI")
            val userIdentity = Identities.newX509Identity(mspId, enrollment)
            wallet.put(userName, userIdentity)
            println("Successfully enrolled user \"$userName\" and imported it into the wallet")
            userIdentity

        }
        return if(result.isFailure) {
            val exception = result.exceptionOrNull()!!
            throw exception
        } else {
            result.getOrThrow()
        }
    }

    private fun registerAdminUser(): X509Identity {

        val adminIdentity = wallet["admin"] as? X509Identity
        return if (adminIdentity != null) {
            println("====================> An identity for the admin user \"admin\" already exists in the wallet")
            adminIdentity
        } else {

            // Enroll the admin user, and import the new identity into the wallet.
            val enrollmentRequestTLS = EnrollmentRequest()
            enrollmentRequestTLS.addHost("localhost")
            enrollmentRequestTLS.profile = "tls"
            val enrollment = hfcaClient.enroll("admin", "adminpw", enrollmentRequestTLS)
            val userIdentity = Identities.newX509Identity(mspId, enrollment)
            wallet.put("admin", userIdentity)
            println("====================> Successfully enrolled user \"admin\" and imported it into the wallet")
            userIdentity
        }

    }
}