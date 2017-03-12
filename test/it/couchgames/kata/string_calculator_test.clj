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

(deftest custom-separator
  (testing "single number, plain character"
    (is (= 10 (string-calculator "//A\n10"))))
  (testing "multiple numbers, plain character"
    (is (= 25 (string-calculator "//A\n10A20A-5"))))
  (testing "multiple numbers, selecting the default separator explicitly"
    (is (= 25 (string-calculator "//,\n10,20,-5"))))
  (testing "multiple numbers, newline still works"
    (is (= 35 (string-calculator "//;\n10\n20;-5\n10"))))
  (testing "multiple numbers, ugly separators"
    (is (= 35 (string-calculator "//$\n10$20$-5$10")))
    (is (= 35 (string-calculator "//.\n10.20.-5.10")))
    (is (= 35 (string-calculator "//]\n10]20]-5]10")))))



