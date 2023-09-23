(ns heartbeat.common.common-test 
  (:require [clojure.test :refer :all]
            [heartbeat.sources.common :as common]))

(deftest parse-colon-separated-test
  (testing "parse-colon-separated positive test(s)"
    (is (= {"A" "1" "B" "2"}
           (common/parse-colon-separated ["\tA\t:\t1" "B:\t\t2kB"]
                                         "kB")))))

(deftest filter-rename-test
  (testing "filter-rename positive test(s)"
    (is (= {"a" 1 "c" 3}
           (common/filter-rename {"A" 1 "B" 2 "C" 3}
                                 {"A" "a" "C" "c"})))))
