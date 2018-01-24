(ns asteroids.lib)

(defmacro component [name params & body]
  `(defn ~name ~params
     (hash-map ~(keyword name)
               (hash-map ~@body))))
