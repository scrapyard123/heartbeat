(defproject heartbeat "0.1.0"
  :description "basic metric collector"
  :license {:name "MIT"}
  :dependencies [[org.clojure/clojure "1.11.1"]]
  :main heartbeat.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
