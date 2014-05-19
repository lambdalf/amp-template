(ns leiningen.new.amp
  (:require [leiningen.new.templates :refer [renderer year project-name
                                             ->files sanitize-ns name-to-path
                                             multi-segment]]
            [leiningen.core.main :as main]))

(defn amp
  "The lein-new template for Alfresco Module Package (AMP) projects."
  [name]
  (let [render  (renderer "amp")
        main-ns (multi-segment (sanitize-ns name))
        data    { :raw-name    name
                  :name        (project-name name)
                  :namespace   main-ns
                  :nested-dirs (name-to-path main-ns)
                  :year        (year) }]
    (main/info "Generating a project called" name "based on the 'amp' template.")
    (main/info "To see other templates (app, plugin, etc), try `lein help new`.")
    (->files data
             [".gitignore"  (render "gitignore" data)]
             ["project.clj" (render "project.clj" data)]
             ["README.md"   (render "README.md" data)]
             ["LICENSE"     (render "LICENSE" data)]
             "amp"
             ["amp/module.properties" (render "module.properties" data)]
             "amp/web"
             "amp/web/css/{{namespace}}"
             "amp/web/images/{{namespace}}"
             "amp/web/scripts/{{namespace}}"
             "amp/config"
             "amp/config/alfresco/extension/templates/webscripts/{{namespace}}"
             "amp/module"
             ["amp/module/module-context.xml" (render "module-context.xml" data)]
             "amp/module/context"
             "amp/licenses"
             ["src/{{nested-dirs}}.clj" (render "core.clj" data)]
             ["test/{{nested-dirs}}_test.clj" (render "test.clj" data)])))
