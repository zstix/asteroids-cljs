(ns asteroids.core
  (:require [asteroids.draw :as draw]))

(enable-console-print!)

; TODO: bring in looping

(def hero {:x 200 :y 200 :rotation 20})

(defn set-stage-fullscreen [canvas]
  (let [width (.-innerWidth js/window)
        height (.-innerHeight js/window)]
    (set! (.-width canvas) width)
    (set! (.-height canvas) height)))

(defn init []
  (let [canvas (.getElementById js/document "world")
        ctx (.getContext canvas "2d")]
    (set-stage-fullscreen canvas)
    (set! (.-lineWidth ctx) 2)
    (set! (.-strokeStyle ctx) "red")
    (draw/asteroid ctx {:x 400 :y 200 :rotation 0})
    (draw/hero ctx hero)))

(init)
