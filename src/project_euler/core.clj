(ns project-euler.core
  "Display each problem, it's solution, and solution speed."
  (:require [project-euler.problems.one :as one]
            [project-euler.problems.two :as two]
            [project-euler.problems.three :as three])
  (:gen-class))

(defn -main
  "####Function
   ####Args:
   None
   ####Description:
   Run and display project euler solutions"
  [& args]
  (let [problem-separator "--------------------------------------------------------------------"]
    (one/euler1-solutions)
    (println problem-separator)
    (two/euler2-solutions)
    (println problem-separator)
    (three/euler3-solutions)))

