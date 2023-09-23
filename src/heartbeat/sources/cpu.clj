(ns heartbeat.sources.cpu
  (:require [clojure.java.shell :as sh]
            [clojure.string :as str]))

(defn parse-output [output]
  (let [[one-min five-min fifteen-min]
        (-> output
            (str/replace #".*?load average:" "")
            (str/replace #"\s*" "")
            (str/split #","))]
    {"cpu-1m" one-min
     "cpu-5m" five-min
     "cpu-15m" fifteen-min}))

(defn get-metrics
  "Obtain CPU metrics from uptime output"
  []
  (parse-output ((sh/sh "uptime" :env {"LC_ALL" "C"}) :out)))
