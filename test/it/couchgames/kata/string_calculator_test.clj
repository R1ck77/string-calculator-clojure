(ns it.couchgames.kata.string-calculator-test
  (:require [clojure.test :refer :all]
            [it.couchgames.kata.string-calculator :refer :all]))

(deftest basic-single-numbers-tests
  (testing "emtpy string"
    (is (= 0 (string-calculator ""))))
  (testing "single number"
    (is (= 42 (string-calculator "42")))
    (is (= -45 (string-calculator "-45")))))

(deftest basic-many-numbers-test
  (testing "two numbers"
    (is (= 552 (string-calculator "465,87")))
    (is (= 1 (string-calculator "0,1")))
    (is (= -2 (string-calculator "-1,-1"))))
  (testing "many numbers"
    (is (= 10 (string-calculator "0,0,1,2,3,4")))
    (is (= -10 (string-calculator "-0,-0,-1,-2,-3,-4")))))

(deftest newline-many-numbers-test
  (testing "two numbers"
    (is (= 378 (string-calculator "465\n-87"))))
  (testing "many numbers"
    (is (= 10 (string-calculator "0,0\n1,2,3\n4")))
    (is (= -10 (string-calculator "-0,-0\n-1,-2\n-3,-4\n")))))


