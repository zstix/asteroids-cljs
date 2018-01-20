(ns asteroids.core
  (:require [asteroids.draw :refer [draw]]
            [asteroids.art :as art]))

(enable-console-print!)

; TODO: bring in looping

(def hero {:pos {:x 200
                 :y 200
                 :a 20}
           :art {:points art/hero
                 :color "red"}})

(def asteroids [{:pos {:x 400
                       :y 200
                       :a 0}
                :art {:points (art/asteroid 30 0)
                      :color "red"}}
                {:pos {:x 800
                       :y 300
                       :a 0}
                 :art {:points (art/asteroid 30 1)
                       :color "red"}}])

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
    (draw ctx (:pos hero) (:art hero))
    (doseq [a asteroids] (draw ctx (:pos a) (:art a) true))))

(init)
