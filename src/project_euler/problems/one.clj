(ns project-euler.problems.one
  "If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.  Find the sum of all the multiples of 3 or 5 below 1000."
  (:require [project-euler.problems.one :as one])
  (:refer-clojure)
  (:gen-class))

(def problem-description "Problem 1:\nIf we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9.\nThe sum of these multiples is 23.\nFind the sum of all the multiples of 3 or 5 below 1000.")

(defn largest-factor
  "####Function
   ####Args:
   num
   : integer

   limit
   : integer

   ####Description:
   Returns the largest factor of num less than limit"
  [num limit]
  (* (quot (- limit 1) num) num))


;;;; Formula for an arithmetic sequence: [(# of terms in sequence)(first term + last term)]/2
(defn sum-of-sequence
  "####Function
   ####Args:
   start
   : integer

   limit
   : integer

   difference
   : integer

   ####Description:
   Returns the sum of arithmetic sequence beginning at start with a common difference of difference, and ending 1 before limit"
  [start limit difference]
  (let [n (quot (- limit 1) difference)
        finish (largest-factor difference limit)]
    (/ (* n (+ start finish)) 2))
)

(defn euler1
  "####Function
   ####Args:
   None
   ####Descripton:
   Returns the solution to problem 1 using the formula for the sum of an arithmetic sequence"
  [& args]
  (let [limit 1000]
    (println (str "euler1: " (- (+ (sum-of-sequence 3 limit 3) (sum-of-sequence 5 limit 5)) (sum-of-sequence 15 limit 15))))))  

;;;; Let's have some fun with lazy sequences
;;   Solve euler1 as a signal processing problem
(defn filter-reduce
  "####Function
   ####Args:
   lst
   : sequence

   filter-func
   : function
 
   reduce-func
   : function

   ####Description:
   Applies filter-func lst, then applies reduce-func to the result of filter-func args and returns the result"
  [lst filter-func reduce-func]
(reduce-func (filter-func lst)))

(defn lazy-arithmetic-seq
  "####Function
   ####Args:
   start
   : integer

   difference
   : integer
   
   ####Description:
   Returns a lazy sequence representing an arithmetic sequence beginning at start and with common difference of difference"
  [start difference]
  (iterate #(+ % difference) start))

(defn filter-less-than-1000
  "####Function
   ####Args:
   lst
   : sequence

   ####Description:
   returns a sequence of all members of lst < 1000"
  [lst]
  (take-while #(< % 1000) lst))

(defn sum-list
  "####Function
   ####Args:
   lst
   : sequence

   ####Description:
   Returns the sum of lst"
  [lst]
  (reduce #(+ %1 %2) 0 lst))

(defn euler1-alternate
  "####Function
   ####Args
   None
   ####Description
   Returns the solution to problem 1 by finding the sum of lazy sequences"
  []
  (let [func #(filter-reduce (lazy-arithmetic-seq % %) filter-less-than-1000 sum-list)]
    (println (str "euler1-alternate: " (- (+ (func 3) (func 5)) (func 15))))))

(defn euler1-solutions
  "####Function
   ####Args:
   None
   ####Description:
   Prints the problem description, result and run time for euler1 and euler1-alternate"
  []
  (println problem-description "\n")
  (time (euler1))
  (println)
  (time (euler1-alternate)))
