(ns little-schemer-in-clj.7-friends-and-relations
  (:use [little-schemer-in-clj.2-again :only (member?)]))

(defn isset? [col]
  (if (empty? col)
    true
    (if (member? (first col) (rest col))
      false
      (recur (rest col)))))

;; mk-set
;; removes duplicates from a list to create a mathematical set in a vector
(defn mkset [col]
  (if (empty? col)
    []
    (if (member? (first col) (rest col))
      (mkset (rest col))
      (cons (first col) (mkset (rest col))))))

;; subset?
;;
(defn subset? [x y]
  (if (empty? x)
    true
    (and (member? (first x) y)
	 (recur (rest x) y))))

;; eqset?
;; true if sets are equivalent
(defn eqset? [x y]
  (and (subset? x y) (subset? y x)))

;; intersect?
;; true if sets intersect
(defn intersect? [x y]
  (if (empty? x)
    false
    (or (member? (first x) y)
	(recur (rest x) y))))

;; intersect
;; returns the intersect of two sets
(defn intersect [x y]
  (if (empty? x)
    []
    (if (member? (first x) y)
      (cons (first x) (intersect (rest x) y))
      (intersect (rest x) y))))

;; union
;;
(defn myunion [x y]
  (if (empty? x)
    y
    (if (member? (first x) y)
      (myunion (rest x) y)
      (cons (first x) (myunion (rest x) y)))))

;; setdiff
;; returns atoms in set x but not in set y
(defn setdiff [x y]
  (if (empty? x)
    []
    (if (member? (first x) y)
      (setdiff (rest x) y)
      (cons (first x) (setdiff (rest x) y)))))

;; intersectall
;; returns intersect of all sets in a list
(defn intersectall [col]
  (if (empty? (rest col))
    (first col)
    (intersect (first col) (intersectall (rest col)))))

;; pair?
;;
(defn pair? [col]
  (= 2 (count col)))
