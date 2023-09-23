(ns heartbeat.sources.timestamp)

(defn get-metrics
  "Obtain timestamp"
  []
  {"timestamp" (str (System/currentTimeMillis))})
