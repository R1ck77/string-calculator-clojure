(ns it.couchgames.kata.string-calculator-test
  (:require [clojure.test :refer :all]
            [it.couchgames.kata.string-calculator :refer :all]))

(deftest basic-single-numbers-tests
  (testing "emtpy string"
    (is (= 0 (string-calculator ""))))
  (testing "single number"
    (is (= 42 (string-calculator "42")))
    (is (= -45 (string-calculator "-45")))))

(deftest basic-two-numbers-test
  (testing "two numbers"
    (is (= 552 (string-calculator "465,87")))))
