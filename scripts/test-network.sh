# curl -sSL https://bit.ly/2ysbOFE | bash -s


./network.sh down
./network.sh up createChannel -ca
cd addOrg3
./addOrg3.sh up -c mychannel
cd ../
./network.sh deployCC -ccn chaincode-trade-lifecycle -ccp ../../projects/fabric-playground/chaincode-trade-lifecycle/ -ccl java -c mychannel
#./network.sh deployCC -ccn chaincode-trade-lifecycle -ccp ../../projects/fabric-playground/chaincode-trade-lifecycle/ -ccl java -ccep "OR('Org1MSP.peer','Org2MSP.peer')"
./network.sh deployCC -ccn chaincode-reference-data -ccp ../../projects/fabric-playground/chaincode-reference-data/ -ccl java -c mychannel
rm -d ./wallet