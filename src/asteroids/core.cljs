(ns asteroids.core
  (:require [cljs.core.async :refer [<! timeout]]
            [asteroids.draw :refer [draw]]
            [asteroids.assets :refer [hero asteroid]]
            [asteroids.comps :refer [pos vel art debug renderable]]
            [asteroids.lib.util :refer [filter-entities]])
  (:require-macros [cljs.core.async.macros :refer [go]]
                   [asteroids.lib.macros :refer [entity]]))

(enable-console-print!)

(defonce canvas (.getElementById js/document "world"))
(defonce ctx (.getContext canvas "2d"))
(defonce world {:width (.-innerWidth js/window)
                :height (.-innerHeight js/window)})

;; ----- STATE ----- ;;

; TODO: better positioning / etc
(defn rand-speed [size] (- (rand size) (/ size 2)))
(def entities [(entity [(pos 200 200 20)
                        (art hero)])
               (entity [(pos (rand (:width world)) (rand (:height world)))
                        (vel (rand-speed 0.3) (rand-speed 0.3) (rand-speed 0.3))
                        (art (asteroid 30 0))
                        (renderable draw)])
               (entity [(pos (rand (:width world)) (rand (:height world)))
                        (vel (rand-speed 0.3) (rand-speed 0.3) (rand-speed 0.3))
                        (renderable draw)
                        (art (asteroid 30 1))])
               (entity [(pos (rand (:width world)) (rand (:height world)))
                        (vel (rand-speed 0.3) (rand-speed 0.3) (rand-speed 0.3))
                        (renderable draw)
                        (art (asteroid 30 2))])
               (entity [(pos (rand (:width world)) (rand (:height world)))
                        (vel (rand-speed 0.3) (rand-speed 0.3) (rand-speed 0.3))
                        (renderable draw)
                        (art (asteroid 30 3))])])

; TODO: can we move these to components?
(def start-state {:running false
                  :start-time 0 ; which of these can we eliminate?
                  :cur-time 0
                  :time-delta 0
                  :entities entities})

(defonce state (atom start-state))

;; ----- UPDATE ----- ;;

; TODO: make bound componenent and check for that
(defn apply-vel [entities]
  (map (fn [e]
         (let [{:keys [pos vel]} e
               {:keys [height width]} world
               x (+ (:x pos) (:x vel))
               y (+ (:y pos) (:y vel))
               a (+ (:a pos) (:a vel))
               new-x (if (> x width) (- x width) 
                       (if (< x 0) (- width (Math/abs x)) x))
               new-y (if (> y height) (- y height) 
                       (if (< y 0) (- height (Math/abs y)) y))]
           (if (contains? e :vel)
             (assoc e :pos {:x new-x :y new-y :a a})
             e)))
       entities))

(defn update-game [state]
  (let [all-e (:entities state)]
    (-> state
        (assoc :entities (apply-vel all-e)))))

;; ----- RENDER ----- ;;

(defn render-game [state]
  (.clearRect ctx 0 0 (:width world) (:height world))
  (let [entities (filter-entities
                   (:entities state)
                   "renderable")]
    (doseq [e entities]
      (let [rend (:fn (:renderable e))]
        (rend ctx e))))
  state)

;; ----- LOOP ----- ;;

(defn time-update [timestamp state]
  (-> state
      (assoc
        :cur-time timestamp
        :time-delta (- timestamp (:start-time state)))
      update-game
      render-game))

(defn time-loop [time]
  (let [new-state (swap! state (partial time-update time))]
    (when (:running new-state)
      (go
        (<! (timeout 30))
        (.requestAnimationFrame js/window time-loop)))))

;; ----- INIT ----- ;

; TODO: do we need the first param?
(defn reset-game [_ cur-time]
  (assoc start-state
         :start-time cur-time
         :running true))

(defn start-game []
  (.requestAnimationFrame
    js/window
    (fn [time]
      (reset! state (reset-game @state time))
      (time-loop time))))

(defn init []
  (set! (.-width canvas) (:width world))
  (set! (.-height canvas) (:height world))
  (set! (.-lineWidth ctx) 2)
  (start-game)) ; TODO: start this with some user interaction

(init)
