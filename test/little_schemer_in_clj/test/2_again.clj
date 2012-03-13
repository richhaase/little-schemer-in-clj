(ns little-schemer-in-clj.test.2-again
  (:use [little-schemer-in-clj.2-again])
  (:use [clojure.test]))

(deftest test-lat?
  (is (lat? [:a :b :c]))
  (is (lat? [1 2 3 4]))
  (is (not (lat? [:a [:b] :c]))))

