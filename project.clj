(defproject project_euler "0.1.0-SNAPSHOT"
  :description "Solutions to various Project Euler problems written in Clojure.  Each problem is assigned it's own namespace."
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/math.numeric-tower "0.0.4"]]
  :plugins [[codox "0.8.13"]]
  :codox {:defaults {:doc/format :markdown}}
  :main ^:skip-aot project-euler.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})

