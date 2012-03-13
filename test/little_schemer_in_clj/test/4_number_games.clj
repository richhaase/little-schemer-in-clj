(ns little-schemer-in-clj.test.4-number-games
  (:use [little-schemer-in-clj.4-number-games])
  (:use [clojure.test]))

(deftest test-add
  (is (= 5 (add 2 3)))
  (is (= -1 (add 2 -3)))
  (is (= 1 (add -2 3)))
  (is (= -5 (add -2 -3))))

(deftest test-sub
  (is (= 1 (sub 3 2)))
  (is (= -1 (sub 2 3)))
  (is (= -5 (sub -3 2)))
  (is (= -1 (sub -3 -2))))

(deftest test-tup?
  (is (tup? [1 2 3 4]))
  (is (tup? []))
  (is (not (tup? [[1 2] 3 4])))
  (is (not (tup? [:a :b :c]))))

(deftest test-addtup
  (is (= 6 (addtup [1 2 3])))
  (is (= 0 (addtup [-1 -2 3])))
  (is (= -4 (addtup [1 -2 -3]))))

(deftest test-mul
  (is (= 6 (mul 2 3)))
  (is (= 9 (mul 3 3))))

(deftest test-tup+
  (is (= [2 4 6 8] (tup+ [1 2 3 4] [1 2 3 4])))
  (is (= [1 3 5 7 9] (tup+ [0 2 4 6 8] [1 1 1 1 1])))
  (is (= [1 2 3] (tup+ [1 2 3] [])))
  (is (= [3 4 5] (tup+ [] [3 4 5]))))

(deftest test-gt?
  (is (gt? 3 2))
  (is (not (gt? 2 3))))

(deftest test-lt?
  (is (lt? 2 3))
  (is (not (lt? 3 2))))

(deftest test-eq?
  (is (eq? 9 9))
  (is (not (eq? 1 2))))

(deftest test-exp
  (is (= 9 (exp 3 2)))
  (is (= 8 (exp 2 3))))

(deftest test-div
  (is (= 3 (div 9 3)))
  (is (= 3 (div 15 4))))

(deftest test-length
  (is (= 2 (length [:a :b])))
  (is (= 5 (length [:this :is 1 2 3])))
  (is (= 0 (length []))))

(deftest test-pick
  (is (= :test (pick 1 [:test :ing])))
  (is (= :last (pick 3 [:this :is :last]))))

(deftest test-rempick
  (is (= [:ing] (rempick 1 [:test :ing])))
  (is (= [:this :is] (rempick 3 [:this :is :last]))))

(deftest test-no-nums
  (is (= [:no :more :numbers] (no-nums [1 :no 2 :more 3 :numbers 4])))
  (is (= [:none :here] (no-nums [:none :here])))
  (is (= [] (no-nums [1 2 3]))))

(deftest test-all-nums
  (is (= [1 2 3 4] (all-nums [1 :no 2 :more 3 :numbers 4])))
  (is (= [] (all-nums [:none :here])))
  (is (= [1 2 3] (all-nums [1 2 3]))))

(deftest test-occur
  (is (= 3 (occur :a [:a :b :c :a :a])))
  (is (= 0 (occur :a [1 2 3])))
  (is (= 0 (occur :a []))))

(deftest test-one?
  (is (one? 1))
  (is (not (one? :a))))