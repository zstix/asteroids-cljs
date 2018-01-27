(ns asteroids.draw
  (:require [asteroids.lib.util :refer [deg-to-rad
                                        get-bounds]]))

; TODO: create a macro to simplify this
(defn save [ctx] (.save ctx) ctx)
(defn restore [ctx] (.restore ctx) ctx)
(defn begin-path [ctx] (.beginPath ctx) ctx)
(defn stroke [ctx] (.stroke ctx) ctx)
(defn move-to [ctx x y] (.moveTo ctx x y) ctx)
(defn line-to [ctx x y] (.lineTo ctx x y) ctx)
(defn arc [ctx x y r a1 a2] (.arc ctx x y r a1 a2) ctx)
(defn set-stroke [ctx color] (set! (.-strokeStyle ctx) color) ctx)
(defn translate [ctx x y] (.translate ctx x y) ctx)
(defn rotate [ctx a] (.rotate ctx a) ctx)

(defn draw-shape [ctx points]
  (let [start (last points)]
    (begin-path ctx)
    (move-to ctx (:x start) (:y start))
    (doseq [p points] (line-to ctx (:x p) (:y p)))
    (stroke ctx))
  ctx)

(defn draw-debug [ctx x y points]
  (let [bounds (get-bounds points)
        full-circle (* 2 Math/PI)]
    (-> ctx
        (save)
        (set-stroke "rgba(100, 100, 100, 0.5)")
        (begin-path)
        (arc 0 0 1 0 full-circle)
        (stroke)
        (begin-path)
        (arc 0 0 bounds 0 full-circle)
        (stroke)
        (restore)))
  ctx)

(defn draw [ctx e]
  (let [{:keys [pos display debug]} e
        {:keys [x y a]} pos
        {:keys [points color]} display]
    (-> ctx
        (save)
        (set-stroke color)
        (translate x y)
        (#(if debug (draw-debug % x y points) %))
        (rotate (deg-to-rad a))
        (draw-shape points)
        (restore))))
