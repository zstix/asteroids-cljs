(ns asteroids.comps)

; NOTE: this might be best moved into a different file
(defn entity [comps] (reduce conj comps))

; TODO: abstract component creation to a macro?

(defn pos [x y & [a]]
  (let [a (or a 0)]
    {(keyword "pos")
     {:x x
      :y y
      :a a}}))

(defn display [points & [color]]
  (let [color (or color "red")]
    {(keyword "display")
     {:points points
      :color color}}))
