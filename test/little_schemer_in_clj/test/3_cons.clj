(ns little-schemer-in-clj.test.3-cons
  (:use [little-schemer-in-clj.3-cons])
  (:use [clojure.test]))

(deftest test-rember?
  (is (= [:c :b :d :f] (rember :a [:c :b :d :a :f])))
  (is (= [:b :d :c :f] (rember :a [:b :d :c :f])))
  (is (= [:c :a :a :a] (rember :a [:c :a :a :a :a]))))

(deftest test-firsts
  (is (= [:a :b :c] (firsts [[:a :b] [:b :c] [:c :d]])))
  (is (= [[:test :this :stuff] :another :time]
	   (firsts [[[:test :this :stuff] :unseen] [:another [:nested :vector]] [:time :up]]))))

(deftest test-seconds
  (is (= [:b :c :d] (seconds [[:a :b] [:b :c] [:c :d]])))
  (is (= [:unseen [:nested :vector] :s]
      (seconds [[[:test :this :stuff] :unseen] [:another [:nested :vector]] [:time :s]]))))

(deftest test-insertR
  (is (= [:a :b :c :d :e :f] (insertR :c :b [:a :b :d :e :f])))
  (is (= [:f :x :g :d :e] (insertR :x :f [:f :g :d :e]))))

(deftest test-insertL
  (is (= [:a :c :b :d :e :f] (insertL :c :b [:a :b :d :e :f])))
  (is (= [:x :f :g :d :e] (insertL :x :f [:f :g :d :e]))))

(deftest test-subst
  (is (= [:what :is :that] (subst :that :this [:what :is :this]))))

(deftest test-subst2
  (is (= [:eatin :cake :and :potatoes]
	   (subst2 :cake :steak :potatoes [:eatin :steak :and :potatoes])))
  (is (= [:now :im :eatin :steak :and :icecream]
	   (subst2 :steak :icecream :cake [:now :im :eatin :cake :and :icecream]))))

(deftest test-multirember
  (is (= [:b :c] (multirember :a [:a :a :b :c :a])))
  (is (= [:coffee :tea :fatigue]
	   (multirember :java [:java :coffee :tea :java :fatigue]))))

(deftest test-multi-insertR
  (is (= [:a :x :a :x :b :c :a :x] (multi-insertR :x :a [:a :a :b :c :a]))))

(deftest test-multi-insertL
  (is (= [:oracle :java :coffee :tea :oracle :java :fatigue]
	   (multi-insertL :oracle :java [:java :coffee :tea :java :fatigue]))))

(deftest test-multi-subst
  (is (= [:programming :clojure :with :clojure]
	   (multi-subst :clojure :java [:programming :java :with :java]))))