(ns leiningen.new.amp
  (:require [leiningen.new.templates :refer [renderer sanitize year project-name
                                             ->files sanitize-ns name-to-path
                                             multi-segment]]
            [leiningen.core.main :as main]))

(defn amp
  "The lein-new template for Alfresco Module Package (AMP) projects."
  [name]
  (let [render  (renderer "amp")
        main-ns (multi-segment (sanitize-ns name))
        data    { :raw-name    name
                  :sanitized-name (sanitize name)
                  :name        (project-name name)
                  :namespace   main-ns
                  :nested-dirs (name-to-path main-ns)
                  :year        (year) }]
    (main/info "Generating a project called" name "based on the 'amp' template.")
    (main/info "To see other templates (app, plugin, etc), try `lein help new`.")
    (->files data
             ; Standard project files
             [".gitignore"  (render "gitignore" data)]
             ["project.clj" (render "project.clj" data)]
             ["README.md"   (render "README.md" data)]
             ["LICENSE"     (render "LICENSE" data)]

             ; Standard Clojure project structure
             "src"
             "src/clojure"
             ["src/clojure/{{nested-dirs}}.clj" (render "core.clj" data)]
             ["test/{{nested-dirs}}_test.clj" (render "test.clj" data)]
             "src/resource"

             ; AMP specific project structure
             "src/resource/alfresco/extension/templates/webscripts/{{sanitized-name}}"
             "src/resource/alfresco/module/{{sanitized-name}}"
             ["src/resource/alfresco/module/{{sanitized-name}}/module-context.xml" (render "module-context.xml" data)]
             "src/resource/alfresco/module/{{sanitized-name}}/context"
             "src/resource/licenses"

             "src/amp"
             ["src/amp/module.properties" (render "module.properties" data)]
             "src/resource/META-INF/resources"
             "src/resource/META-INF/resources/html/{{sanitized-name}}"
             "src/resource/META-INF/resources/css/{{sanitized-name}}"
             "src/resource/META-INF/resources/images/{{sanitized-name}}"
             "src/resource/META-INF/resources/scripts/{{sanitized-name}}")))
