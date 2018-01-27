(ns asteroids.lib.util)

(defn deg-to-rad [deg] (* deg (/ Math/PI 180)))

(defn get-bounds
  "Get the collision distance (radius) for a set of points"
  [points]
  (reduce
    (fn [size point]
      (max size
           (Math/abs (:x point))
           (Math/abs (:y point))))
    0 points))

(defn filter-entities
  "Gets the entities with a provided component key"
  [entities component]
  (filter
    (fn [entity]
      (contains? entity (keyword component)))
    entities))
