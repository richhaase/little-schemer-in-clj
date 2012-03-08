(ns little-schemer-in-clj.3-cons)

;; rember
;; remove an atom from a collection
;; (rember :a [:c :b :d :a :f])
;; (rember :a [:b :d :c :f])
;; (rember :a [:c :a :a :a :a])
(defn rember [a col]
  (cond
   (empty? col) col
   (= a (first col)) (rest col)
   :else (cons (first col) (rember a (rest col)))))

;; firsts
;; takes the first item from a list of lists
;; (firsts [[:a :b] [:b :c] [:c :d]])
;; (firsts [[[:test :this :stuff] :unseen] [:another [:nested :vector]] [:time :up]])
;;
;; definitely the right way to do this, but avoids the purpose of the exercise
;; (defn firsts [col]
;;   (map first col))
;;
(defn firsts [col]
  (if (empty? col)
    []
    (cons (ffirst col) (firsts (rest col)))))

;; seconds
;; takes tthe second item from a list of lists
;; (seconds [[:a :b] [:b :c] [:c :d]])
;; (seconds  [[[:test :this :stuff] :unseen] [:another [:nested :vector]] [:time :s]])
;;
;; definitely the right way to do this, but avoids the purpose of the exercise
;; (defn seconds [col]
;;   (map second col))
;;
(defn seconds [col]
  (if (empty? col)
    []
    (cons (second (first col)) (seconds (rest col)))))

;; insertR
;; takes new old and col
;; returns col with the atom new inserted to the right of the first instance of the
;; atom old
;; (insertR :c :b [:a :b :d :e :f])
;; (insertR :x :f [:f :g :d :e])
(defn insertR [new old col]
  (cond
   (empty? col) []
   (= old (first col)) (concat [old new] (rest col))
   :else (cons (first col) (insertR new old (rest col)))))

;; insertL
;; takes new old and col
;; returns col with the atom new inserted to the left of the first instance of the
;; atom old
;; (insertL :c :b [:a :b :d :e :f])
;; (insertL :x :f [:f :g :d :e])
(defn insertL [new old col]
  (cond
   (empty? col) []
   (= old (first col)) (cons new col)
   :else (cons (first col) (insertL new old (rest col)))))

;; subst
;; takes new old and col
;; returns col with the first instance of old replaced by new
;; (subst :that :this [:what :is :this])
(defn subst [new old col]
  (cond
   (empty? col) []
   (= old (first col)) (cons new (rest col))
   :else (cons (first col) (subst new old (rest col)))))

;; subst2
;; replaces the first instance of o1 or o2 with the value of new in col
;; (subst2 :cake :steak :potatoes [:eatin :steak :and :potatoes])
;; (subst2 :steak :icecream :cake [:now :im :eatin :cake :and :icecream])
(defn subst2 [new o1 o2 col]
  (cond
   (empty? col) []
   (or (= o1 (first col)) (= o2 (first col))) (cons new (rest col))
   :else (cons (first col) (subst2 new o1 o2 (rest col)))))

;; multirember
;; removes all instances of an atom from a collection
;; (multirember :a [:a :a :b :c :a])
;; (multirember :java [:java :coffee :tea :java :fatigue])
(defn multirember [a col]
  (cond
   (empty? col) []
   (= a (first col)) (multirember a (rest col))
   :else (cons (first col) (multirember a (rest col)))))

;; multi-insertR
;; inserts new value to the right of every instance of old value in a collection
;; (multi-insertR :x :a [:a :a :b :c :a])
(defn multi-insertR [new old col]
  (cond
   (empty? col) []
   (= old (first col)) (concat [old new] (multi-insertR new old (rest col)))
   :else (cons (first col) (multi-insertR new old (rest col)))))

;; multi-insertL
;; inserts new value to the left of every instance of old value in a collection
;; (multi-insertL :oracle :java [:java :coffee :tea :java :fatigue])
(defn multi-insertL [new old col]
  (cond
   (empty? col) []
   (= old (first col)) (concat [new old] (multi-insertL new old (rest col)))
   :else (cons (first col) (multi-insertL new old (rest col)))))

;; multi-subst
;; replaces every instance of old with new in collection
;; (multi-subst :clojure :java [:programming :java :with :java])
(defn multi-subst [new old col]
  (cond
   (empty? col) []
   (= old (first col)) (cons new (multi-subst new old (rest col)))
   :else (cons (first col) (multi-subst new old (rest col)))))