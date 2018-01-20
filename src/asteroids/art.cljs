(ns asteroids.art)

(def hero [{:x 0 :y -20}
           {:x 16 :y 20}
           {:x -16 :y 20}])

(defn asteroid [size n]
  (let [points [[{:x size :y (* -1.5 size)}
                 {:x 0 :y (* -1.5 size)}
                 {:x (* -1 size) :y (* -1 size)}
                 {:x (* -1.5 size) :y 0}
                 {:x (* -1.5 size) :y (* 0.5 size)}
                 {:x (* -1 size) :y size}
                 {:x (* -1 size) :y (* 1.5 size)}
                 {:x (* 0.5 size) :y (* 1.5 size)}
                 {:x size :y size}
                 {:x (* 1.5 size) :y 0}
                 {:x (* 1.5 size) :y (* -0.5 size)}]
                [{:x 10 :y 10}
                 {:x 20 :y 20}
                 {:x -10 :y 30}]]]
    (points n)))
