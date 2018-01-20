(ns asteroids.core
  (:require [asteroids.draw :refer [draw]]
            [asteroids.art :as art])
  (:use [asteroids.comps :only [position display]]))

(enable-console-print!)

; TODO: bring in looping

; TODO: store this stuff in a global state?
; TODO: clean up further
(def hero {:pos (position 200 200 20)
           :display (display art/hero)})
(print hero)

(def asteroids [{:pos (position 300 200)
                 :display (display (art/asteroid 30 0))}
                {:pos (position 400 200)
                 :display (display (art/asteroid 30 1))}])

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
    (draw ctx (:pos hero) (:display hero))
    (doseq [a asteroids] (draw ctx (:pos a) (:display a) true))))

(init)
