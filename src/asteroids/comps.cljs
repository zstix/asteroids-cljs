(ns asteroids.comps
  (:require-macros [asteroids.lib :refer [component]]))

(component pos [x y & [a]]
           :x x
           :y y
           :a (or a 0))

(component display [points & [color]]
           :points points
           :color (or color "red"))

(component debug [])
