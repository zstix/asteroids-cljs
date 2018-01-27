(ns asteroids.comps
  (:require-macros [asteroids.lib.macros :refer [component]]))

(component pos [x y & [a]]
           :x x
           :y y
           :a (or a 0))

(component art [points & [color]]
           :points points
           :color (or color "red"))

(component debug [])

(component renderable [fn]
           :fn fn)
