(ns it.couchgames.kata.string-calculator
  (:gen-class))

(defn- string-to-integer [s]
  (case s
    "" 0
    (let [v (Integer/valueOf s)]
      (if (> v 1000) 0 v))))

(defn- drop-this [to-drop s]
  (apply str (drop (count to-drop) s)))

(defn- parse-separator-line [s]
  (let [[all sep1 sep2] (first (re-seq #"//(\[(.+)\]|.)\n" s))]
    (assert (or (not (nil? sep1)) (not (nil? sep2))))
    (if (nil? sep2)
      [sep1 (drop-this all s)]
      [sep2 (drop-this all s)])))

(defn- get-separator [s]
  (if (= \/ (first s))
    (parse-separator-line s)
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
