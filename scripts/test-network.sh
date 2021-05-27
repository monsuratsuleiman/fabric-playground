# curl -sSL https://bit.ly/2ysbOFE | bash -s


./network.sh down
./network.sh up createChannel -ca
./network.sh deployCC -ccn chaincode-trade-lifecycle -ccp ../../projects/fabric-playground/chaincode-trade-lifecycle/ -ccl java
rm -d ./wallet