(ns utopian-library.kafka-utils.consumer
  (:require [clj-kafka.consumer.zk :as k.consumer]
            [utopian-library.kafka-utils.serializers :as serializer]))

(defn consumer []
  (let [consumer-config {"zookeeper.connect"  "localhost:2181"
                         "group.id"           "clj-kafka.consumer"
                         "auto.offset.reset"  "smallest"
                         "auto.commit.enable" "true"
                         "auto.offset.reset.config" "earliest"}
        consumer-connector (k.consumer/consumer  consumer-config)
        d {:key-decoder (k.consumer/default-decoder) :value-decoder (serializer/custom-decoder)}]
    (let [messages (k.consumer/messages consumer-connector "json-test" d)]
      (doseq [message messages]
        (println (:value message))))
    (k.consumer/shutdown consumer-connector)))

(consumer)
