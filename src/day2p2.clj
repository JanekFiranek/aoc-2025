(ns day2p2
  (:require [clojure.string :as str]))

(defn parse-range [range-str]
  (let [[_ from to] (re-find #"(\d+)-(\d+)" range-str)]
    (range (parse-long from) (+ (parse-long to) 1))))

(defn invalid-repeating? [number]
  (let [s (str number)
        doubled (str s s)
        search-space (subs doubled 1 (dec (count doubled)))]
    (str/includes? search-space s)))

(defn -main []
  (-> (read-line)
      (str/split #",")
      (#(mapcat parse-range %))
      (distinct)
      (#(filter invalid-repeating? %))
      (#(reduce + %))
      prn))