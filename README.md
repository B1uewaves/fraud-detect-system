1. Csv and parse CSV
2. determine fraud logic: (layer1; use java for simle filteiring layer2: use ml scoring )
2. use kafka to simulate high-frequency data stream

kafka: user designed distribution strategy of data queue(partition of events). 1 consumer can have all? no problem(create multiple consumers, a partition can only be consumed by 1 consumer, but 1 consumer can consume multiple complete partition). topic= tv channel. Record = a msg
Why kafka: horixoantl scalable, high=throughput
msg with a key. hash(key)% N = partition 
offset = position in partition, act as a counter, eg next to read is offset 3
broker = a group of partitions(can contain multiple topics)
leader-follower (consumer reads from leader, follower act as backup)


Dataset Variable Descriptions:
step
Maps a unit of time in the real world. In this dataset, 1 step equals 1 hour elapsed since the first transaction.

type
Type of the transaction. Possible values include: CASH-IN, CASH-OUT, DEBIT, PAYMENT, and TRANSFER.

amount
The amount of money transferred in the transaction, expressed in local currency.

nameOrig
The customer ID who initiated the transaction.

oldbalanceOrg
The initial balance of the originator before the transaction.

newbalanceOrig
The balance of the originator after the transaction.

nameDest
The recipient's customer ID for the transaction.

oldbalanceDest
The initial balance of the recipient before the transaction.

newbalanceDest
The balance of the recipient after the transaction.

isFraud
Binary label identifying fraudulent transactions (1 means fraud, 0 means non-fraud).

isFlaggedFraud
Flag for illegal attempts to transfer amounts greater than 200,000 in a single transaction (1 means flagged, 0 means not flagged).

Fraud Proportion by Type:
       type  Fraud_Proportion
0   CASH_IN          0.000000
1  CASH_OUT          0.001840
2     DEBIT          0.000000
3   PAYMENT          0.000000
4  TRANSFER          0.007688