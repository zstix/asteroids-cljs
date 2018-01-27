(ns asteroids.lib.macros)

(defmacro component [name params & body]
  `(defn ~name ~params
     (hash-map ~(keyword name)
               (hash-map ~@body))))

(defmacro entity [comps]
  `(reduce conj ~comps))
