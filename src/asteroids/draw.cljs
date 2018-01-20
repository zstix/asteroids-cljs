(ns asteroids.draw)

(defn deg-to-rad [deg] (* deg (/ Math/PI 180)))

(defn draw-shape [ctx points]
  (let [start (last points)]
    (.beginPath ctx)
    (.moveTo ctx (:x start) (:y start))
    (doseq [i points] (.lineTo ctx (:x i) (:y i)))
    (.stroke ctx)))

(defn draw-entity [ctx {:keys [x y rotation]} points]
  (.save ctx)
  (.translate ctx x y)
  (.rotate ctx (deg-to-rad rotation))
  ; (.beginPath ctx)
  ; (.arc ctx 0 0 1 0 (* 2 Math/PI))
  ; (.stroke ctx)
  (draw-shape ctx points)
  (.restore ctx))

(defn hero [ctx pos]
  (let [points [{:x 0 :y -20}
                {:x 16 :y 20}
                {:x -16 :y 20}]]
    (draw-entity ctx pos points)))

; TODO: store differnt asteroids in col
(defn asteroid [ctx pos]
  (let [size 30
        points [{:x size :y (* -1.5 size)}
                {:x 0 :y (* -1.5 size)}
                {:x (* -1 size) :y (* -1 size)}
                {:x (* -1.5 size) :y 0}
                {:x (* -1.5 size) :y (* 0.5 size)}
                {:x (* -1 size) :y size}
                {:x (* -1 size) :y (* 1.5 size)}
                {:x (* 0.5 size) :y (* 1.5 size)}
                {:x size :y size}
                {:x (* 1.5 size) :y 0}
                {:x (* 1.5 size) :y (* -0.5 size)}]]
    (draw-entity ctx pos points)))
