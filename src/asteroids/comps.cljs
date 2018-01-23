(ns asteroids.comps)

(defn pos [x y & [a]]
  (let [a (or a 0)]
    {:x x
     :y y
     :a a}))

(defn display [points & [color]]
  (let [color (or color "red")]
    {:points points
     :color color}))
