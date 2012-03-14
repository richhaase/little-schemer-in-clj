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

(defn addR [new old col]
  (concat [old new] col))

(defn addL [new old col]
  (concat [new old] col))

(defn seqS [new old col]
  (cons new col))

(defn seqrem [new old col]
  col)

(defn insert-g [func]
  (fn [new old col]
    (cond
     (empty? col) []
     (= old (first col))
     (func new old ((insert-g func) new old (rest col)))
     :else (cons (first col) ((insert-g func) new old (rest col))))))

(defn insertL [new old col]
  ((insert-g addL) new old col))
	
(defn insertR [new old col]
  ((insert-g addR) new old col))

(defn subst [new old col]
  ((insert-g seqS) new old col))

(defn rember [a col]
  ((insert-g seqrem) false a col))

(defn multirember-f [testfunc]
  (fn [a col]
    (cond
     (empty? col) []
     (= a (first col)) ((multirember-f testfunc) a (rest col))
     :else (cons (first col) ((multirember-f testfunc) a (rest col))))))

(def multirember-eq?
     (multirember-f =))

(def eq?-tuna
     (eq?-c :tuna))

(def multirember-tuna
     (fn [func col]
       (cond
	(empty? col) []
	(func (first col)) (multirember-tuna (rest col))
	:else (cons (first col) (multirember-tuna (rest col))))))

(defn evens-only* [l]
  (cond
   (empty? l) []
   (coll? (first l)) (cons (evens-only* (first l)) (evens-only* (rest l)))
   :else
   (if (even? (first l))
     (cons (first l) (evens-only* (rest l)))
     (evens-only* (rest l)))))

(defn ps-collect [newl prod sum]
  (concat [sum prod] newl))

(comment ;;done playing with this for the moment
(defn evens-only&collector [l collector]
  (cond
   (empty? l) (collector [] 1 0)
   (coll? (first l)) (evens-only&collector)))

)