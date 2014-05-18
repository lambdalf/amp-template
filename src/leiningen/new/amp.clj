(ns leiningen.new.amp
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "amp"))

(defn amp
  "The lein-new template for Alfresco Module Package (AMP) projects."
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info (str "Creating new AMP project '" name "'..."))
    (->files data
             [".gitignore"  (render "gitignore")]
             ["project.clj" (render "project.clj")]
             ["README.md"   (render "README.md")]
             "amp/web"
             "amp/resources"
             "amp/module"
             "amp/licenses"
             "src/{{sanitized}}"
             "test/{{sanitized}}")))
