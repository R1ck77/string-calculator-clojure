(ns it.couchgames.kata.string-calculator-test
  (:require [clojure.test :refer :all]
            [it.couchgames.kata.string-calculator :refer :all]))

(deftest basic-single-numbers-tests
  (testing "emtpy string"
    (is (= 0 (string-calculator "")))))
