(ns utopian-library.kafka-utils.serializers
  (:require [cheshire.core :as json])
  (:import (kafka.serializer Decoder)))

(defn json-bytes-to-map [bytes]
  (let [json-str (String. bytes "UTF-8")]
    (json/parse-string json-str true)))

(defn map-to-json-bytes [data]
  (let [json-str (json/generate-string data)]
    (.getBytes json-str "UTF-8")))

(defn custom-decoder []
  (proxy [Decoder] []
    (fromBytes [^bytes bytes]
      (json-bytes-to-map bytes))))
