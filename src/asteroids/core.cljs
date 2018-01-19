(ns asteroids.core)

(enable-console-print!)

; TODO: bring in looping

; TODO: move her to different file
(def hero {:x 100 :y 100 :width 100 :height 100})

; TODO: figure out how to repl this for faster dev
(defn draw-hero [ctx {:keys [x y width height]}]
  (.beginPath ctx)
  (.moveTo ctx x y)
  (.lineTo ctx x (+ y height))
  (.lineTo ctx (+ x width) (+ y height))
  (.lineTo ctx (+ x width) y)
  (.lineTo ctx x y)
  (.stroke ctx))

(defn set-stage-fullscreen [canvas]
  (let [width (.-innerWidth js/window)
        height (.-innerHeight js/window)]
    (set! (.-width canvas) width)
    (set! (.-height canvas) height)))

(defn init []
  (let [canvas (. js/document (getElementById "world"))
        ctx (.getContext canvas "2d")]
    (set-stage-fullscreen canvas)
    (set! (.-strokeStyle ctx) "red")
    (draw-hero ctx hero)))

(init)
