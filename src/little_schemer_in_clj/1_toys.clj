(ns little-schemer-in-clj.1-toys)

;; atom?
(defn atom? [x]
  (not (coll? x)))

;;Random code snippets


(= :milk (rest [:soured :milk]))
;; false

(= :milk (last [:soured :milk]))
;; true


