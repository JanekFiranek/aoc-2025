(ns day1p2
  (:require [clojure.string :as str]))

(defn parse [line]
  (let [[_ dir amount] (re-find #"([RL])(\d+)" line)]
    [dir (parse-long amount)]))

(defn move [start end]
  (if (> end start)
    (- (long (Math/floor (/ end 100.0)))
       (long (Math/floor (/ start 100.0))))

    (- (long (Math/ceil (/ start 100.0)))
       (long (Math/ceil (/ end 100.0))))))

(defn cmd [state [dir amount]]
  (let [start-pos   (:position state)

        end-pos     (case dir
                      "L" (- start-pos amount)
                      "R" (+ start-pos amount)
                      start-pos)

        hits        (move start-pos end-pos)

        final-state (assoc state :position (mod end-pos 100))]

    (update final-state :password + hits)))

(defn -main []
  (let [initial-state {:position 50 :password 0}
        lines         (-> *in* slurp str/split-lines)]
    (prn (reduce (fn [current-state next-line]
                   (cmd current-state (parse next-line)))
                 initial-state
                 lines))))