(ns it.couchgames.kata.string-calculator
  (:gen-class))

(defn- string-to-integer [s]
  (case s
    "" 0
    (let [v (Integer/valueOf s)]
      (if (> v 1000) 0 v))))

(defn- drop-this [to-drop s]
  (apply str (drop (count to-drop) s)))

(defn- extract-separators [s]
  (if (= (count s) 1)
    [s "\n"]
    (let [m (re-matcher #"\[(.+?)\]" s)]
      (loop [xs ["\n"]]
        (if (.find m)
          (recur (conj xs (.group m 1)))
          xs)))))

(defn- extract-sep-definition-body [s]
  (let [[[all body] _] (re-seq #"//(\[.+\]|.)\n" s)]
    [body (drop-this all s)]))

(defn- parse-separator-line [s]
  (let [[xs body] (extract-sep-definition-body s)
        xsep (extract-separators xs)]
    [xsep body]))

(defn- get-separators [s]
  (if (= \/ (first s))
    (parse-separator-line s)
    [ ["," "\n"] s]))

(defn- separators-to-regexp [xs]
  (re-pattern
   (str "("
        (apply str (interpose "|"
                    (map #(java.util.regex.Pattern/quote %)
                         xs)))
        ")")))

(defn string-calculator [s]
  (let [[xs s] (get-separators s)
        xn (map string-to-integer 
                (clojure.string/split s (separators-to-regexp xs)))
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
