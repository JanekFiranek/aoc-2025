(ns day1p1
  (:require [clojure.string :as str]))

(defn parse [line]
  (let [[_ dir amount] (re-find #"([RL])(\d+)" line)]
    [dir (parse-long amount)]))

(defn cmd [state [dir amount]]
  (let [moved-state (case dir
                      "L" (update state :position - amount)
                      "R" (update state :position + amount)
                      state)

        final-state (update moved-state :position mod 100)]

    (cond-> final-state
            (zero? (:position final-state)) (update :password inc))))

(defn -main []
  (let [initial-state {:position 50 :password 0}
        lines         (-> *in* slurp str/split-lines)]

    (prn (reduce (fn [current-state next-line]
                   (cmd current-state (parse next-line)))
                 initial-state
                 lines))))