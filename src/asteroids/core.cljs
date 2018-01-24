(ns asteroids.core
  (:require [asteroids.draw :refer [draw]]
            [asteroids.art :refer [hero asteroid]])
  (:use [asteroids.comps :only [pos display debug]]))

(enable-console-print!)

; TODO: bring in looping

(def state [[(pos 200 200 20)
             (display hero)]
            [(pos 300 200)
             (display (asteroid 30 0))
             (debug)]
            [(pos 400 200)
             (display (asteroid 30 1))
             (debug)]])

(defn get-entities [state]
  (reduce
    (fn [all e]
      (conj all (reduce conj e)))
    []
    state))

(defn set-stage-fullscreen [canvas]
  (let [width (.-innerWidth js/window)
        height (.-innerHeight js/window)]
    (set! (.-width canvas) width)
    (set! (.-height canvas) height)))

(defn init []
  (let [canvas (.getElementById js/document "world")
        ctx (.getContext canvas "2d")
        st (get-entities state)]
    (set-stage-fullscreen canvas)
    (set! (.-lineWidth ctx) 2)
    (doseq [e st] (draw ctx e))))

(init)
