(ns little-schemer-in-clj.test.8-lambda
  (:use little-schemer-in-clj.8-lambda)
  (:use clojure.test))

(deftest test-rember-f
  (is (= [:a :c] (rember-f = :b [:a :b :c])))
  (is (= [:b] (rember-f #(not (= %1 %2)) :b [:a :b :c]))))

(deftest test-rember-f2
  (is (= [:a :c] ((rember-f2 =) :b [:a :b :c])))
  (is (= [:b] ((rember-f2 #(not (= %1 %2))) :b [:a :b :c]))))

(deftest test-insertR
  (is (= [:a :b :c :d :e :f] (insertR :c :b [:a :b :d :e :f])))
  (is (= [:f :x :g :d :e] (insertR :x :f [:f :g :d :e]))))

(deftest test-insertL
  (is (= [:a :c :b :d :e :f] (insertL :c :b [:a :b :d :e :f])))
  (is (= [:x :f :g :d :e] (insertL :x :f [:f :g :d :e]))))

(deftest test-subst
  (is (= [:what :is :that] (subst :that :this [:what :is :this]))))

(deftest test-rember?
  (is (= [:c :b :d :f] (rember :a [:c :b :d :a :f])))
  (is (= [:b :d :c :f] (rember :a [:b :d :c :f])))
  (is (= [:c] (rember :a [:c :a :a :a :a]))))

(deftest test-multirember-eq?
  (is (= [:b :c] (multirember-eq? :a [:a :a :b :c :a])))
  (is (= [:coffee :tea :fatigue]
	   (multirember-eq? :java [:java :coffee :tea :java :fatigue]))))