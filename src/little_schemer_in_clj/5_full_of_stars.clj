(ns little-schemer-in-clj.5-full-of-stars)

;; rember*
;; same as rember except it recurs into nested collections
(defn rember* [x col]
  (if (empty? col)
    []
    (if (coll? (first col))
      (cons (rember* x (first col)) (rember* x (rest col)))
      (if (= x (first col))
	(rember* x (rest col))
	(cons (first col) (rember* x (rest col)))))))

;; insertR*
;; same as insertR except that it recurs into nested collections
(defn insertR* [old new col]
  (if (empty? col)
    []
    (if (coll? (first col))
      (cons (insertR* old new (first col)) (insertR* old new (rest col)))
      (if (= old (first col))
	(concat [old new] (insertR* old new (rest col)))
	(cons (first col) (insertR* old new (rest col)))))))

;; occur*
;; recursive count of all atoms a in a list of atoms or a list of lists of atoms
(defn occur* [a col]
  (if (empty? col)
    0
    (if (coll? (first col))
      (+ (occur* a (first col)) (occur* a (rest col)))
      (if (= a (first col))
	(+ 1 (occur* a (rest col)))
	(+ 0 (occur* a (rest col)))))))

;; subst*
;; substitute old for new at any level of a nested collection
(defn subst* [old new col]
  (if (empty? col)
    []
    (if (coll? (first col))
      (cons (subst* old new (first col)) (subst* old new (rest col)))
      (if (= old (first col))
	(cons new (subst* old new (rest col)))
	(cons (first col) (subst* old new (rest col)))))))

;; insertL*
;; same as insertL except that it recurs into nested collections
(defn insertL* [old new col]
  (if (empty? col)
    []
    (if (coll? (first col))
      (cons (insertL* old new (first col)) (insertL* old new (rest col)))
      (if (= old (first col))
	(concat [new old] (insertL* old new (rest col)))
	(cons (first col) (insertL* old new (rest col)))))))

;; member*
;; true if x is a member of the nested collection col
(defn member* [x col]
  (if (empty? col)
    false
    (if (coll? (first col))
      (or (member* x (first col)) (member* x (rest col)))
      (if (= x (first col))
	true
	(member* x (rest col))))))

;; leftmost
;; finds the leftmost atom in a nested set of lists, assuming a leftmost value is not
;; nil
(defn leftmost [col]
  (if (coll? (first col))
    (recur (first col))
    (first col)))

;; eqlist?
;; true if a two nested collections are equal
(defn eqlist? [c1 c2]
  (cond
   (and (empty? c1) (empty? c2)) true
   (or (empty? c1) (empty? c2)) false
   (and (coll? (first c1)) (coll? (first c2))) (and
						(eqlist? (first c1) (first c2))
						(eqlist? (rest c1) (rest c2)))
   (or (coll? (first c1)) (coll? (first c2))) false
   (= c1 c2) (eqlist?
	      (rest c1)
	      (rest c2))))
   
   
    