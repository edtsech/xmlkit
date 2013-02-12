(ns xmlkit.core
  (:require [clojure.data.xml :as xml]))

(defn- find-first
  [pred coll]
  (first (filter pred coll)))

(defmacro find-el
  "Usage:
   Let's find a first element with tag :Comment
   inside an element with tag :Comments
   (find-el input :Comments :Comment)"
  [el & tags]
  (if (= 1 (count tags))
      `(find-by-tag ~(first tags) ~el)
      `(find-el (find-by-tag ~(first tags) ~el) ~@(rest tags))))

(defmacro find-el->>
   "Usage:
   Let's find a text of the first comment
   (find-el->> input [:Data :Comments] :content first text)"
   [el tags & funcs]
  `(->> (find-el (find-by-tag ~(first tags) ~el)
                 ~@(rest tags))
        ~@funcs))

(defn tag? [el]
  (:tag el))

(defn tag= [tag]
  (fn [x] (= tag (:tag x))))

(defn text
  "Return text of node."
  [el]
  (cond
    (string? el) el
    (tag? el) (apply str (map text (:content el)))
    :else ""))

(defn find-by-tag
  "Fint first element with tag which is child of el."
  [tag el]
  (find-first (tag= tag) (:content el)))



