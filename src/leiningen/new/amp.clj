(ns leiningen.new.amp
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(defn amp
  "The lein-new template for Alfresco Module Package (AMP) projects."
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}
        render #((renderer "amp") % data)]
    (main/info (str "Generating a project called " name " based on the 'amp' template."))
    (->files data
             [".gitignore"  (render "gitignore")]
             ["project.clj" (render "project.clj")]
             ["README.md"   (render "README.md")]
             ["LICENSE"     (render "LICENSE")]
             "amp"
             ["amp/module.properties" (render "module.properties")]
             "amp/web"
             "amp/web/css/{{sanitized}}"
             "amp/web/images/{{sanitized}}"
             "amp/web/scripts/{{sanitized}}"
             "amp/config"
             "amp/config/alfresco/extension/templates/webscripts"
             "amp/module"
             ["amp/module/module-context.xml" (render "module-context.xml")]
             "amp/module/contexts"
             "amp/licenses"
             "src/{{sanitized}}"
             "test/{{sanitized}}")))
