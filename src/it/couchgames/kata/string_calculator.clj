(ns it.couchgames.kata.string-calculator
  (:gen-class))

(defn- string-to-integer [s]
  (case s
    "" 0
    (let [v (Integer/valueOf s)]
      (if (> v 1000) 0 v))))

(defn- get-separator [s]
  (if (= \/ (first s))
    [(str (nth s 2)) (apply str (drop 4 s))]
    [ "," s]))

(defn- separator-to-regexp [s]
  (re-pattern
   (str "(" (java.util.regex.Pattern/quote s) "|\n)")))

(defn string-calculator [s]
  (let [[sep s] (get-separator s)
        xn (map string-to-integer 
                (clojure.string/split s (separator-to-regexp sep)))
        neg (filter #(< % 0) xn)]
    (if (not (empty? neg))
      (throw
       (UnsupportedOperationException.
        (str "Negative numbers not supported (" (apply str (interpose "," neg)) ")")))
      (apply + xn))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
