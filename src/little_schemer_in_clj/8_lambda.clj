(ns little-schemer-in-clj.8-lambda)

;; rember-f
;;
(defn rember-f [f x col]
  (if (empty? col)
    []
    (if (f x (first col))
      (rember-f f x (rest col))
      (cons (first col) (rember-f f x (rest col))))))

(defn rember-f2 [f]
  (fn [x col]
    (if (empty? col)
      []
      (if (f x (first col))
	((rember-f2 f) x (rest col))
	(cons (first col) ((rember-f2 f) x (rest col)))))))

;; eq?-c
;;
(defn eq?-c [a]
  (fn [x] (= a x)))

(defn addR [old new col]
  (concat [old new] col))

(defn addL [old new col]
  (concat [new old] col))

;; insertL-f
(defn insertL-f [f]
  (fn [old new col]
    (if (empty? col)
      []
      (if (f old (first col))
	(addL old new ((insertL-f f) old new (rest col)))
	(cons (first col) ((insertL-f f) old new (rest col)))))))

;; insertL-f
(defn insertR-f [f]
  (fn [old new col]
    (if (empty? col)
      []
      (if (f old (first col))
	(addR old new ((insertR-f f) old new (rest col)))
	(cons (first col) ((insertR-f f) old new (rest col)))))))

(defn addR [old new col]
  (concat [old new] col))

(defn addL [old new]
  (concat [new old] col))

(defn insert-g [adder]
  (fn [old new col]
    (cond
     (empty? col) []
     (= old (first col)) (adder old new ((insert-g adder) old new (rest col)))
     :else (cons (first col) ((insert-g adder) old new (rest col))))))

(defn insertL [old new col]
  (insert-g addL))
	
