(ns heartbeat.sources.memory
  (:require [clojure.string :as str]
            [heartbeat.sources.common :as common]))

(def metric-map
  {"MemTotal" "mem-total"
   "MemFree" "mem-free"
   "MemAvailable" "mem-available"
   "SwapTotal" "swap-total"
   "SwapFree" "swap-free"})

(defn get-metrics
  "Obtain memory metrics from /proc/meminfo"
  []
  (common/filter-rename
   (common/parse-colon-separated
    (str/split-lines (common/read-special-file "/proc/meminfo"))
    "kB")
   metric-map))
