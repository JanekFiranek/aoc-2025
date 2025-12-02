(ns day2p1
  (:require [clojure.string :as str]))

(defn parse-range [range-str]
  (let [[_ from to] (re-find #"(\d+)-(\d+)" range-str)]
    (range (parse-long from) (+ (parse-long to) 1))))

(defn valid? [number]
  (let [s (str number)
        len (count s)]
    (if (odd? len)
      true
      (let [mid (/ len 2)
            first-half (subs s 0 mid)
            second-half (subs s mid)]
        (not= first-half second-half)))))

(defn -main []
  (-> (read-line)
      (str/split #",")
      (#(mapcat parse-range %))
      (distinct)
      (#(remove valid? %))
      (#(reduce + %))
      prn))