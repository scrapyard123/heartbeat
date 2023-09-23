(ns heartbeat.core
  (:gen-class)
  (:require [clojure.string :as str]
            [heartbeat.sources.battery :as battery]
            [heartbeat.sources.cpu :as cpu]
            [heartbeat.sources.memory :as memory]
            [heartbeat.sources.timestamp :as timestamp]))

(def metric-functions
  "Add additional metric sources there"
  [timestamp/get-metrics
   cpu/get-metrics
   memory/get-metrics
   battery/get-metrics])

(defn generate-output
  "Gather metrics and produce output in key=value format"
  []
  (str/join " "
            (map (fn [[key value]] (str key "=" value))
                 (seq (into {} (map #(%) metric-functions))))))

#_{:clj-kondo/ignore [:unused-binding]}
(defn -main [& args]
  (println (generate-output))
  (shutdown-agents))
