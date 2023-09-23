(ns heartbeat.sources.common
  (:require [clojure.string :as str]))

(defn read-special-file
  "https://clojuredocs.org/clojure.core/slurp#example-542692d5c026201cdc327086"
  [f]
  (slurp (java.io.FileReader. f)))

(defn parse-colon-separated
  "Parse output in colon-separated key value format"
  [output-lines default-unit]
  (into {}
        (filter #(= (count %) 2)
                (map #(-> %1
                          (str/replace #"\s+" "")
                          (str/replace (re-pattern default-unit) "")
                          (str/split #":"))
                     output-lines))))

(defn filter-rename
  "Filter out entries based on key map and then rename keys according to the same map"
  [map key-map]
  (let [filtered-map (select-keys map (keys key-map))]
    (reduce
     (fn [map [key value]]
       (assoc map (key-map key) value))
     {}
     filtered-map)))
