(ns little-schemer-in-clj.test.1-toys
  (:use [little-schemer-in-clj.1-toys])
  (:use [clojure.test]))

(deftest test-atom?
  (is (not (atom? '())))
  (is (not (atom? {})))
  (is (not (atom? [])))
  (is (not (atom? #{})))
  (is (atom? :a))
  (is (atom? "test"))
  (is (atom? \t))
  (is (atom? 1)))
