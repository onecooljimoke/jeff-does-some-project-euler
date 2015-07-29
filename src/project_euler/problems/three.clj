(ns project-euler.problems.three
  "The prime factors of 13195 are 5, 7, 13 and 29.
   What is the largest prime factor of the number 600851475143 ?"
  (:require [clojure.math.numeric-tower :as math])
  (:refer-clojure))


(def problem-description "The prime factors of 13195 are 5, 7, 13 and 29. What is the largest prime factor of the number 600851475143 ?")

(defn factor
  "####Function
   ####Args:
   info-map
   : map with two properties: num & largest-prime-factor

   num
   : integer

   ####Description:
   Returns a new info-map after continuously factoring factor from :num"  
  [info-map factor]
  (loop [result info-map]
    (cond 
     (= 0 (mod (:num result) factor)) (recur {:num (/ (:num result) factor) :largest-prime-factor factor})
     :else result)))

(defn factor-odds
  "####Function
   ####Args:
   info-map
   : map with two properties: num & largest-prime-factor

   ####Description:
   Returns the largest prime factor after factoring odd numbers"
  [info-map]
  (loop [result info-map
         current-factor 3]
    (cond 
     ;;continue to factor while current-factor is less than the square root of num
     (< current-factor (math/sqrt (:num result))) (recur (factor result current-factor) (+ current-factor 2))
     ;;check :num in case it's greater than 1
     :else (if (= 1 (:num result)) (:largest-prime-factor result) (:num result)))))

(defn euler3
  "####Function
   ####Args:
   num
   : integer

   ####Description:
   Returns the solution to problem 3"
  [num]
  (let [after-two (factor {:num num :largest-prime-factor 0} 2)]
    (cond 
     (= 1 (:num after-two)) ((println (str "euler3: " 2)))
     :else (println (str "euler3: " (factor-odds after-two))))))

(defn euler3-solutions
  "####Function
   ####Args:
   None
   ####Description:
   Prints the problem description, result and run time for euler3"
  []
  (println problem-description "\n")
  (time (euler3 600851475143)))
