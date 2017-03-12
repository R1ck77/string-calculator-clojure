(ns it.couchgames.kata.string-calculator
  (:gen-class))

(defn- string-to-integer [s]
  (case s
    "" 0
    (Integer/valueOf s) ))

(defn string-calculator [s]
  (string-to-integer s))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
