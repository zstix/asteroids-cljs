(ns asteroids.draw)

(defn deg-to-rad [deg] (* deg (/ Math/PI 180)))

(defn draw-shape [ctx points]
  (let [start (last points)]
    (.beginPath ctx)
    (.moveTo ctx (:x start) (:y start))
    (doseq [p points] (.lineTo ctx (:x p) (:y p)))
    (.stroke ctx)))

(defn draw-debug [ctx x y]
  (.beginPath ctx)
  (.arc ctx 0 0 1 0 (* 2 Math/PI))
  (.stroke ctx))

(defn draw [ctx
            {:keys [x y a]}
            {:keys [points color]}
            & debug]
  (.save ctx)
  (set! (.-strokeStyle ctx) color)
  (.translate ctx x y)
  (.rotate ctx (deg-to-rad a))
  (if debug (draw-debug ctx x y))
  (draw-shape ctx points)
  (.restore ctx))
