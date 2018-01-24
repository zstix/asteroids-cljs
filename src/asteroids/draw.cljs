(ns asteroids.draw)

(defn deg-to-rad [deg] (* deg (/ Math/PI 180)))

(defn draw-shape [ctx points]
  (let [start (last points)]
    (.beginPath ctx)
    (.moveTo ctx (:x start) (:y start))
    (doseq [p points] (.lineTo ctx (:x p) (:y p)))
    (.stroke ctx)))

(defn draw-debug [ctx x y points]
  (let [bounds (reduce
                 (fn [size point]
                   (max size
                        (Math/abs (:x point))
                        (Math/abs (:y point))))
                 0 points)]
    (.save ctx)
    (set! (.-strokeStyle ctx) "grey")
    (.beginPath ctx)
    (.arc ctx 0 0 1 0 (* 2 Math/PI))
    (.stroke ctx)
    (.beginPath ctx)
    (.arc ctx 0 0 bounds 0 (* 2 Math/PI))
    (.stroke ctx)
    (.restore ctx)))

(defn draw [ctx
            {{:keys [x y a]} :pos
             {:keys [points color]} :display
             :keys [debug]}]
  (.save ctx)
  (set! (.-strokeStyle ctx) color)
  (.translate ctx x y)
  (.rotate ctx (deg-to-rad a))
  (if debug (draw-debug ctx x y points))
  (draw-shape ctx points)
  (.restore ctx))
