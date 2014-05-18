(ns leiningen.new.alfresco-amp-template
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "alfresco-amp-template"))

(defn alfresco-amp-template
  "FIXME: write documentation"
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' alfresco-amp-template project.")
    (->files data
             [".gitignore"  (render "gitignore")]
             ["project.clj" (render "project.clj")]
             ["README.md"   (render "README.md")]
             "web-resources"
             "amp-resources"
             "amp-config"
             "amp-licenses"
             "resources"
             "src"
             "test")))
