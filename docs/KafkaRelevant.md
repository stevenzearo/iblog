### Kafka Notes
#### 查看topic信息
1. 查看所有topic  
> kafka-topic.sh --list --zookeeper <zookeeper host>:<zookeeper port>
2. 查看topic描述信息
> kafka-topic.sh --describe --zookeeper <zookeeper host>:<zookeeper port> --topic <topic name>  
3. 查看topic具体信息
> kafka-console-consumer.sh --bootstrap-server <kafka host>:<kakfa port> --topic <topic name> --from-beginning
