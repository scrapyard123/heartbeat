(ns heartbeat.sources.battery
  (:require [clojure.java.shell :as sh]
            [clojure.string :as str]
            [heartbeat.sources.common :as common]))

(def metric-map
  {"state" "bat-state"
   "percentage" "bat-percentage"})

(defn get-metrics
  "Obtain battery metrics from upower output"
  []
  (common/filter-rename
   (common/parse-colon-separated
    (str/split-lines
     ;; TODO: Don't hardcode battery ID
     ((sh/sh "upower" "-i" "/org/freedesktop/UPower/devices/battery_BAT0") :out))
    "%")
   metric-map))
