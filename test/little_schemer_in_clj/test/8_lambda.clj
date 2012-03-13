(ns little-schemer-in-clj.test.8-lambda
  (:use little-schemer-in-clj.8-lambda)
  (:use clojure.test))

(deftest test-rember-f
  (is (= [:a :c] (rember-f = :b [:a :b :c])))
  (is (= [:b] (rember-f #(not (= %1 %2)) :b [:a :b :c]))))

(deftest test-rember-f2
  (is (= [:a :c] ((rember-f2 =) :b [:a :b :c])))
  (is (= [:b] ((rember-f2 #(not (= %1 %2))) :b [:a :b :c]))))