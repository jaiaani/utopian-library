(ns utopian-library.core
  (:require [clj-time.core :as t]
            [utopian-library.kafka-utils.producer :as producer]))

(defn -main
  [& args]
    (println "What do you wanna do?\n
    a) Get a book
    b) Give a book back")
  (let [input (read-line)]
    (cond (= "a" input) (let [as-of {:as-of (str (t/today))}]
                        (println "Rule: You have until 1 month to read")
                        (println "Insert your reader-id")
                        (let [reader-id (assoc as-of :reader-id (read-line))]
                          (println "Insert the book-id")
                          (let [book-picked-up (assoc reader-id :book-id (read-line))]
                            (producer/send-menssage! "json-test" "teste" book-picked-up)))))))
