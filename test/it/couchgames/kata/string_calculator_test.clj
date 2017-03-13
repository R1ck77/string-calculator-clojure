(ns it.couchgames.kata.string-calculator-test
  (:require [clojure.test :refer :all]
            [it.couchgames.kata.string-calculator :refer :all]))

(deftest basic-single-numbers-tests
  (testing "emtpy string"
    (is (= 0 (string-calculator ""))))
  (testing "single number"
    (is (= 42 (string-calculator "42")))
    (is (thrown-with-msg?
         UnsupportedOperationException
         #"Negative numbers not supported \(-45\)"
         (string-calculator "-45")))))

(deftest big-numbers-are-ignored
  (testing "single number"
    (is (= 0 (string-calculator "1001")))
    (is (= 1000 (string-calculator "1000"))))
  (testing "multiple numbers"
    (is (= 0 (string-calculator "1001,1002,1003")))
    (is (= 1 (string-calculator "1010,1,10000")))))

(deftest basic-many-numbers-test
  (testing "two numbers"
    (is (= 552 (string-calculator "465,87")))
    (is (= 1 (string-calculator "0,1")))
    (is (thrown-with-msg?
         UnsupportedOperationException
         #"Negative numbers not supported \(-1,-1\)"
         (string-calculator "-1,-1"))))
  (testing "many numbers"
    (is (= 10 (string-calculator "0,0,1,2,3,4")))
    (is (thrown-with-msg?
         UnsupportedOperationException
         #"Negative numbers not supported \(-1,-2,-3,-4\)"
         (string-calculator "-0,-0,-1,-2,-3,-4")))))

(deftest newline-many-numbers-test
  (testing "two numbers"
    (is (thrown-with-msg?
         UnsupportedOperationException
         #"Negative numbers not supported \(-87\)"
         (string-calculator "465\n-87"))))
  (testing "many numbers"
    (is (= 10 (string-calculator "0,0\n1,2,3\n4")))
    (is (thrown-with-msg?
         UnsupportedOperationException
         #"Negative numbers not supported \(-1,-2,-3,-4\)"
         (string-calculator "-0,-0\n-1,-2\n-3,-4\n")))))

(deftest custom-separator
  (testing "single number, plain character"
    (is (= 10 (string-calculator "//A\n10"))))
  (testing "multiple numbers, plain character"
    (is (thrown-with-msg?
         UnsupportedOperationException
         #"Negative numbers not supported \(-5\)"
         (string-calculator "//A\n10A20A-5"))))
  (testing "multiple numbers, selecting the default separator explicitly"
    (is (thrown-with-msg?
         UnsupportedOperationException
         #"Negative numbers not supported \(-5\)"
         (string-calculator "//,\n10,20,-5"))))
  (testing "multiple numbers, newline still works"
    (is (thrown-with-msg?
         UnsupportedOperationException
         #"Negative numbers not supported \(-5\)"
         35 (string-calculator "//;\n10\n20;-5\n10"))))
  (testing "multiple numbers, ugly separators"
    (is (= 45 (string-calculator "//$\n10$20$5$10")))
    (is (= 45 (string-calculator "//.\n10.20.5.10")))
    (is (= 45 (string-calculator "//]\n10]20]5]10")))))

(deftest long-delimiters
  (testing "kata's sample"
    (is (= 6 (string-calculator "//[:::]\n1:::2:::3")))))

