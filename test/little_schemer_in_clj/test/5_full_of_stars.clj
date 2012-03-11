(ns little-schemer-in-clj.test.5-full-of-stars
  (:use [little-schemer-in-clj.5-full-of-stars])
  (:use [clojure.test]))

(deftest test-rember*
  (is (= [[:java :solaris] :mysql]
	   (rember* :oracle [:oracle [:oracle :java :solaris :oracle] :mysql])))
  (is (= [:none [[[:found]]]]
	   (rember* :oracle [:none [[[:found]]]]))))

(deftest test-insertR*
  (is (= [1 0 2 1 0 3 1 0 4] (insertR* 1 0 [1 2 1 3 1 4])))
  (is (= [[:this [:is [:a :test]]] :as [:a :test] :a :test]
	   (insertR* :a :test [[:this [:is [:a]]] :as [:a] :a])))
  (is (= [] (insertR* :any :other [])))
  (is (= [:n [[[[[:m]]]]]] (insertR* :x :y [:n [[[[[:m]]]]]]))))

(deftest test-occur*
  (is (= 2 (occur* :java [[:java] :oracle :sun :apache [[[[:java] :clr]]]])))
  (is (= 0 (occur* :none [[:java] :oracle :sun :apache [[[[:java] :clr]]]]))))

(deftest test-subst*
  (is (= [[[:clr]] :microsoft] (subst* :java :clr [[[:java]] :microsoft])))
  (is (= [1 [2 [3 [4]]]] (subst* :a :b [1 [2 [3 [4]]]]))))

(deftest test-insertL*
  (is (= [0 1 2 0 1 3 0 1 4] (insertL* 1 0 [1 2 1 3 1 4])))
  (is (= [[:this [:is [:test :a]]] :as [:test :a] :test :a]
	   (insertL* :a :test [[:this [:is [:a]]] :as [:a] :a])))
  (is (= [] (insertL* :any :other [])))
  (is (= [:n [[[[[:m]]]]]] (insertR* :x :y [:n [[[[[:m]]]]]]))))

(deftest test-member*
  (is (member* :is [:this [[:is]] :a [:match]]))
  (is (not (member* :is [:this [[[:isnt]] :a] [[:match]]]))))

(deftest test-leftmost
  (is (= :potato (leftmost [[[:potato] :chips] :and [[:fish :chips]]])))
  (is (= :hot (leftmost [[:hot :tuna] :and [[[[:cheese]]]]])))
  (is (nil? (leftmost [[[[] :four] 17] :bliggity [:blah]]))))

(deftest test-eqlist?
  (is (eqlist? [:this [[:is] :a] :an :equal [:list]]
	       [:this [[:is] :a] :an :equal [:list]]))
  (is (eqlist? [:sausage [:and :biscuits] [[:rock]]]
	       [:sausage [:and :biscuits] [[:rock]]]))
  (is (not (eqlist? [:strawberry [[:short] :cake]]
		    [[:strawberry] [:short :cake]]))))