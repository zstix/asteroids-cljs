(ns asteroids.core
  (:require [asteroids.draw :refer [draw]]
            [asteroids.art :as art])
  (:use [asteroids.comps :only [entity pos display debug]]))

(enable-console-print!)

; TODO: bring in looping

(def state [(entity [(pos 200 200 20)
                     (display art/hero)])
            (entity [(pos 300 200)
                     (display (art/asteroid 30 0))
                     (debug)])
            (entity [(pos 400 200)
                     (display (art/asteroid 30 1))
                     (debug)])])

(print (first state))

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
    (doseq [e state] (draw ctx e))))

(init)
