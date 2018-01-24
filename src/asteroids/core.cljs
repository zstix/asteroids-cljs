(ns asteroids.core
  (:require [asteroids.draw :refer [draw]]
            [asteroids.art :refer [hero asteroid]]
            [asteroids.comps :refer [entity]])
  (:use [asteroids.comps :only [pos display debug]]))

(enable-console-print!)

(defonce canvas (.getElementById js/document "world"))
(defonce ctx (.getContext canvas "2d"))

(def state [(entity [(pos 200 200 20)
                     (display hero)])
            (entity [(pos 300 200)
                     (display (asteroid 30 0))
                     (debug)])
            (entity [(pos 400 200)
                     (display (asteroid 30 1))
                     (debug)])])

; TODO: bring in looping
(defn init []
  (do
    (set! (.-width canvas) (.-innerWidth js/window))
    (set! (.-height canvas) (.-innerHeight js/window))
    (set! (.-lineWidth ctx) 2)
    (doseq [e state] (draw ctx e))))

(init)
