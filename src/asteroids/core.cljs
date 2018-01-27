(ns asteroids.core
  (:require-macros [asteroids.lib.macros :refer [entity]])
  (:require [asteroids.draw :refer [draw]]
            [asteroids.art :refer [hero asteroid]])
  (:use [asteroids.comps :only [pos display debug]]))

(enable-console-print!)

(defonce canvas (.getElementById js/document "world"))
(defonce ctx (.getContext canvas "2d"))
(defonce world {:width (.-innerWidth js/window)
                :height (.-innerHeight js/window)})

(def state [(entity [(pos 200 200 20)
                     (display hero)])
            (entity [(pos 300 200)
                     (display (asteroid 30 0))])
            (entity [(pos 400 200)
                     (display (asteroid 30 1))])
            (entity [(pos 500 200)
                     (display (asteroid 30 2))])
            (entity [(pos 600 200)
                     (display (asteroid 30 3))])])

; TODO: bring in looping
(defn init []
  (do
    (set! (.-width canvas) (:width world))
    (set! (.-height canvas) (:height world))
    (set! (.-lineWidth ctx) 2)
    (doseq [e state] (draw ctx e))))

(init)
