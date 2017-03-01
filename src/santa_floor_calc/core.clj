(ns santa-floor-calc.core
  (:gen-class))

(defn to-value [c]
  (case c
    \( 1
    \) -1))

(defn calc
  [^String s]
  (let [basef (fn [fvals]
                (loop [floor 0 index 0]
                  (let [cv (get fvals index)
                        cf (+ floor (or cv 0))
                        step (inc index)]
                    (cond (< cf 0) step
                          (nil? cv) :never
                          :else (recur cf step)))))
        fvals (vec (map to-value s))]
    {:result-floor (reduce + 0 fvals)
     :basement-position (basef fvals)}))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Input:")
  (let [s (read-line)
        r (calc s)]
    (println r)))
