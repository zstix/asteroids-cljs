(ns asteroids.lib.macros)

(defmacro component [n params & body]
  `(defn ~n ~params
     (hash-map ~(keyword n)
               (hash-map ~@body))))

(defmacro entity [comps]
  `(reduce conj ~comps))
