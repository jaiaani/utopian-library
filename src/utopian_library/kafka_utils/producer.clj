(ns utopian-library.kafka-utils.producer
  (:require [clj-kafka.producer :as k.producer]
            [utopian-library.kafka-utils.serializers :as serializer])
  (:import (org.apache.log4j BasicConfigurator)))
(use 'clojure.tools.logging)

(defn start-logging []
  (BasicConfigurator/configure))

;(start-logging)

(def producer-properties {"metadata.broker.list" "localhost:9092"
                          "serializer.class" "kafka.serializer.DefaultEncoder"
                          "partitioner.class" "kafka.producer.DefaultPartitioner"})

(def producer (k.producer/producer producer-properties))

(defn send-menssage!
  [topic key value]
  (k.producer/send-message producer
                           (k.producer/message topic
                                               (.getBytes key)
                                               (serializer/map-to-json-bytes value))))
