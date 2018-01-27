(ns asteroids.assets)

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
                [{:x (* 0.7 size) :y (* -1.5 size)}
                 {:x 0 :y (* -1 size)}
                 {:x (* -0.7 size) :y (* -1.5 size)}
                 {:x (* -1.5 size) :y (* -0.3 size)}
                 {:x (* -1.3 size) :y (* 1.3 size)}
                 {:x (* -0.7 size) :y (* 1.5 size)}
                 {:x (* 0.7 size) :y (* 1.5 size)}
                 {:x (* 1.5 size) :y size}
                 {:x (* 1.3 size) :y (* 0.3 size)}
                 {:x (* 1.5 size) :y 0}]
                [{:x (* -1 size) :y (* -1.3 size)}
                 {:x (* -0.3 size) :y (* -0.6 size)}
                 {:x (* -1.3 size) :y (* -0.6 size)}
                 {:x (* -1.3 size) :y 0}
                 {:x (* -0.6 size) :y (* 1.3 size)}
                 {:x (* 0.3 size) :y size}
                 {:x (* 0.6 size) :y (* 1.3 size)}
                 {:x (* 1.3 size) :y (* 0.3 size)}
                 {:x (* 0.3 size) :y 0}
                 {:x (* 1.5 size) :y (* -0.3 size)}
                 {:x (* 1.3 size) :y (* -0.6 size)}
                 {:x 0 :y (* -1.3 size)}]
                [{:x 0 :y (* -1.5 size)}
                 {:x (* -1 size) :y (* -1 size)}
                 {:x (* -0.6 size) :y (* -0.3 size)}
                 {:x (* -1.3 size) :y (* 0.3 size)}
                 {:x (* -0.6 size) :y (* 1.3 size)}
                 {:x (* -0.3 size) :y size}
                 {:x size :y (* 1.5 size)}
                 {:x (* 1.5 size) :y size}
                 {:x size :y 0}
                 {:x (* 1.5 size) :y (* -0.3 size)}
                 {:x size :y (* -1.3 size)}]]]
    (points n)))
