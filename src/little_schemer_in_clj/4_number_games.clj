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
;; checks for a tuple, is true if only a list/vector of integers is found
;; 
(defn tup? [col]
  (cond
   (empty? col) true
   (= java.lang.Long (type (first col))) (recur (rest col))
   :else false)
  )

;; addtup
;; adds a tuple, basically performs (reduce + col)
;;
(defn addtup [col]
  (if (empty? col)
    0
    (add (first col) (addtup (rest col)) ))
  )

;; mul
;; mul = *, only handles positive integers
(defn mul [x y]
  (cond
   (zero? y) 0
   (neg? y) (+ x (mul x (inc y)))
   :else (+ x (mul x (dec y))))
  )

;; tup+
;; adds items in a pair of tuples
(defn tup+ [col1 col2]
  (cond
   (and (empty? col1) (empty? col2)) []
   (empty? col1) col2
   (empty? col2) col1
   :else (cons (+ (first col1) (first col2)) (tup+ (rest col1) (rest col2))))
  )

;; gt?
;; greater than
(defn gt? [x y]
  (cond
   (= 0 x) false
    (= 0 y) true
    :else (recur (dec x) (dec y))))
        
;; lt?
;; less than 
(defn lt? [x y]
  (cond
   (= 0 x) true
   (= 0 y) false
   :else (recur (dec x) (dec y))))

;; eq?
;; equals
(defn eq? [x y]
  (cond
   (and (= 0 x) (= 0 y)) true
   (or (= 0 x) (= 0 y)) false
   :else (recur (dec x) (dec y))))

;; exp
;; exponent
(defn exp [x y]
  (cond
   (= 0 y) 1
   :else (mul x (exp x (dec y)))))

;; div
;; same as quot
(defn div [x y]
  (cond
   (< x y) 0
   :else (+ 1 (div (- x y) y))))

;; length
;; gets the length of a collection
(defn length [col]
  (cond
   (empty? col) 0
   :else (+ 1 (length (rest col)))))

;; pick
;; gets the value at position specified by x
(defn pick [x col]
  (cond
   (zero? (dec x)) (first col)
   :else (recur (dec x) (rest col))))

;; rempick
;; removes the item at the position specified by x
(defn rempick [x col]
  (cond
   (zero? (dec x)) (rest col)
   :else (cons (first col) (rempick (dec x) (rest col)))))

;; no-nums
;; removes all the numbers from a list
(defn no-nums [col]
  (cond
   (empty? col) []
   (number? (first col)) (no-nums (rest col))
   :else (cons (first col) (no-nums (rest col)))))

;; all-nums
;; removes all non-numbers from a list
(defn all-nums [col]
  (cond
   (empty? col) []
   (number? (first col)) (cons (first col) (all-nums (rest col)))
   :else (all-nums (rest col))))

;; occur
;; counts the number of occurences of the atom specified by a
(defn occur [a col]
  (if (empty? col)
    0
    (if (= a (first col))
      (+ 1 (occur a (rest col)))
      (occur a (rest col)))))

;; one?
(defn one? [x]
  (= 1 x))