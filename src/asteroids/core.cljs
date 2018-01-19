(ns asteroids.core
  (:require [asteroids.draw :as draw]))

(enable-console-print!)

; TODO: bring in looping

; TODO: abstract this to utils
(defn deg-to-rad [deg] (* deg (/ Math/PI 180)))

(def hero {:x 200 :y 200 :size 40 :angle (deg-to-rad 20)})

(defn set-stage-fullscreen [canvas]
  (let [width (.-innerWidth js/window)
        height (.-innerHeight js/window)]
    (set! (.-width canvas) width)
    (set! (.-height canvas) height)))

(defn init []
  (let [canvas (.getElementById js/document "world")
        ctx (.getContext canvas "2d")]
    (set-stage-fullscreen canvas)
    (set! (.-strokeStyle ctx) "red")
    (draw/hero ctx hero)))

(init)
