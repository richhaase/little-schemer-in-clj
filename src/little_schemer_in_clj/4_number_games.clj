(ns little-schemer-in-clj.4-number-games)

;; add
;; function to add two numbers
;; works for all integers
;; (add 2 3)
;; (add 2 -3)
;; (add -2 3)
;; (add -2 -3)
(defn add [x y]
  (cond 
   (= 0 y) x
   (neg? y) (recur (dec x) (inc y))
   :else (recur (inc x) (dec y)))
  )

;; sub
;; function to subtract two numbers
;; works for all integers
;; (sub 3 2)
;; (sub 2 3)
;; (sub -3 2)
;; (sub -3 -2)
(defn sub [x y]
  (cond
   (= 0 y) x
   (neg? y) (recur (inc x) (inc y))
   :else (recur (dec x) (dec y)))
  )

;; tup?
;; checks for a tuple, is true if only a list/vector of numbers is found
(defn tup? [col]
  (cond
   (empty? col) false
   (