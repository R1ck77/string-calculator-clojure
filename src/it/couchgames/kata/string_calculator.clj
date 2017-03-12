(ns it.couchgames.kata.string-calculator
  (:gen-class))

(defn- string-to-integer [s]
  (case s
    "" 0
    (Integer/valueOf s) ))

(defn- get-separator [s]
  (if (= \/ (first s))
    [ (str (nth s 2)) (apply str (drop 4 s))]
    [ "," s]))

(defn- separator-to-regexp [s]
  (re-pattern
   (str "(" (java.util.regex.Pattern/quote s) "|\n)")))

(defn string-calculator [s]
  (let [[sep s] (get-separator s)]
    (apply + 
           (map string-to-integer 
                (clojure.string/split s (separator-to-regexp sep))))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
