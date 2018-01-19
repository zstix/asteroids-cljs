(ns asteroids.draw)

(defn hero [ctx {:keys [x y size angle]}]
  (let [a {:x 0 :y (- 0 (/ size 2))}
        b {:x (+ 0 (/ size 2.5)) :y (+ 0 (/ size 2))}
        c {:x (- 0 (/ size 2.5)) :y (+ 0 (/ size 2))}]
    (.save ctx)
    (.translate ctx x y)
    (.rotate ctx angle)
    (.beginPath ctx)
    (.moveTo ctx (:x a) (:y a))
    (.lineTo ctx (:x b) (:y b))
    (.lineTo ctx (:x c) (:y c))
    (.lineTo ctx (:x a) (:y a))
    (.stroke ctx)
    (.restore ctx)))
