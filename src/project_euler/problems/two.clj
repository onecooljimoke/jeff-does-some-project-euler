(ns project-euler.problems.two
  "
Each new term in the Fibonacci sequence is generated by adding the previous two terms. By starting with 1 and 2, the first 10 terms will be: 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, ... By considering the terms in the Fibonacci sequence whose values do not exceed four million, find the sum of the even-valued terms."
  (:require [project-euler.problems.two :as two])
  (:refer-clojure)
  (:gen-class))

(def problem-description "Problem 2:\nEach new term in the Fibonacci sequence is generated by adding the previous two terms.\nBy starting with 1 and 2, the first 10 terms will be: 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, ...\nBy considering the terms in the Fibonacci sequence whose values do not exceed four million, find the sum of the even-valued terms.")

(defn euler2
  "####Function
   ####Args:
   None
   
   ####Description:
   Solves euler2 by generating the next three members of the fibonacci sequence and adding the third member to a sum, display the solution and solution speed"
  []
  (loop [fib1 1
         fib2 1
         evenfib 2
         sum 0]
    (cond 
     (< evenfib 4000000) (let [newfib1 (+ fib2 evenfib)
                          newfib2 (+ evenfib newfib1)
                          newevenfib (+ newfib1 newfib2)]
                      (recur newfib1 newfib2 newevenfib (+ sum evenfib)))
     :else (println (str "euler2: " sum)))))

(defn lazy-fiblist
  "####Function
   ####Args:
   None

   ####Description:
   Returns a lazy sequence of fibonacci numbers"
  []
  ;; Taken from https://en.wikibooks.org/wiki/Clojure_Programming/Examples/Lazy_Fibonacci, lazy sequence inside anonymous function 
  ;; helps with garbage collection issues
  ((fn fiblist [fib1 fib2]
      (cons fib1 (lazy-seq (fiblist fib2 (+ fib1 fib2))))) 0 1))

(defn filter-even-fibs
  "####Function
   ####Args: 
   fiblist
   : sequence || lazy-sequence

   ####Description:
   Returns a sequence of even fibonacci numbers < 4000000 "

  [fiblist]
  (filter #(even? %) (take-while #(< % 4000000) fiblist)))

(defn sum-list
  "####Function
   ####Args:
   fiblist
   : sequence

   ####Description:
   Returns the sum of a sequence"
  [fiblist]
  (reduce + fiblist))

(defn euler2-alternate
  "####Function
   ####Args:
   None

   ####Description:
   Solves euler2 by filtering & reducing a lazy-sequence of fibonacci numbers, displays the solution and solution speed"
  []
  (let [even-fib-sum (sum-list (filter-even-fibs (lazy-fiblist)))]
    (println (str "euler2-alternate: " even-fib-sum))))

(defn euler2-solutions
  "####Function
   ####Args:
   None
   ####Description:
   Prints the problem description, result and run time for euler2 and euler2-alternate"
  []
  (println problem-description "\n")
  (time (euler2))
  (println)
  (time (euler2-alternate)))
