(ns little-schemer-in-clj.test.7-friends-and-relations
  (:use little-schemer-in-clj.7-friends-and-relations)
  (:use clojure.test))

(deftest test-isset?
  (is (isset? [:a :b :c :d 1 2 3]))
  (is (not (isset? [:a :a 1 3]))))

(deftest test-mkset
  (is (= [:a :b :c 1 2 3] (mkset [1 2 3 :c :a :b :b :b :c 1 2 3]))))

(deftest test-subset?
  (is (subset? [:a :b 1] [1 2 3 :a :b :c]))
  (is (not (subset? [:a :b :d 1] [1 2 3 :a :b :c]))))

(deftest test-eqset?
  (is (eqset? [:a :b 1 :c :3] [:3 :a :c :b 1]))
  (is (not (eqset? [:a :b] [:a :b 1]))))

(deftest test-intersect?
  (is (intersect? [:a :b :c :C] [:c :C :d]))
  (is (not (intersect? [:a :b :c] [1 2 3]))))

(deftest test-intersect
  (is (= [:c :C] (intersect [:a :b :c :C] [:c :C :d])))
  (is (= [] (intersect [:a :b :c] [1 2 3]))))

(deftest test-myunion
  (is (= [:this :is :a :test] (myunion [:this :is :a] [:a :test]))))

(deftest test-setdiff
  (is (= [:b :c] (setdiff [:a :b :c 1 2 3] [:a :B :C 1 2 3]))))

(deftest test-intersectall
  (is (= [:a 1]
	   (intersectall [[:a :b 1 2 3] [:a 2 3 1] [:a :C :D :e 1 :f]]))))

(deftest test-apair?
  (is (pair? [:a :b]))
  (is (pair? [[:a [:b]] :c]))
  (is (not (pair? [:a :b :c]))))