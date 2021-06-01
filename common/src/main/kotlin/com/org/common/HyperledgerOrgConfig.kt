package com.org.common

import java.nio.file.Path
import java.nio.file.Paths

class HyperledgerOrgConfig(val pemFilePath: String, val walletPath: String,
                           val mspId: String, val affiliation: String,
                           val networkConfigPath: Path,
                           val certificateAuthorityUrl: String
)

val Org1Config = HyperledgerOrgConfig(
    "../../fabric-samples/test-network/organizations/peerOrganizations/org1.example.com/ca/ca.org1.example.com-cert.pem",
    "wallet/org1",
    "Org1MSP",
    "org1.department1",
    Paths.get(
    "..",
    "..",
    "fabric-samples",
    "test-network",
    "organizations",
    "peerOrganizations",
    "org1.example.com",
    "connection-org1.yaml"
    ),
    "https://localhost:7054"
)

val Org2Config = HyperledgerOrgConfig(
    "../../fabric-samples/test-network/organizations/peerOrganizations/org2.example.com/ca/ca.org2.example.com-cert.pem",
    "wallet/org2",
    "Org2MSP",
    "org2.department1",
    Paths.get(
    "..",
    "..",
    "fabric-samples",
    "test-network",
    "organizations",
    "peerOrganizations",
    "org2.example.com",
    "connection-org2.yaml"
    ),
    "https://localhost:8054"
)

val Org3Config = HyperledgerOrgConfig(
    "../../fabric-samples/test-network/organizations/peerOrganizations/org3.example.com/ca/ca.org3.example.com-cert.pem",
    "wallet/org3",
    "Org3MSP",
    "",
    Paths.get(
    "..",
    "..",
    "fabric-samples",
    "test-network",
    "organizations",
    "peerOrganizations",
    "org3.example.com",
    "connection-org3.yaml"
    ),
    "https://localhost:11054"
)