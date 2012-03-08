(ns little-schemer-in-clj.2-again)

;; lat?
;; true if every element of a list is an atom
(defn lat? [col]
  (cond
   (empty? col) true
   (coll? (first col)) false
   :else (recur (rest col))))

;; member?
;; true if x is in col
(defn member? [x col]
  (cond
   (empty? col) false
   :else (or (= (first col) x) (recur x (rest col)))))
