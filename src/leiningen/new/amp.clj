(ns leiningen.new.amp
  (:require [leiningen.new.templates :refer [renderer sanitize year project-name
                                             ->files sanitize-ns name-to-path
                                             multi-segment]]
            [leiningen.core.main :as main]))

(defn amp
  "The lein-new template for Alfresco Module Package (AMP) projects."
  [name]
  (let [render         (renderer "amp")
        main-ns        (multi-segment (sanitize-ns name))
        sanitized-name (sanitize name)
        module-id      (clojure.string/replace sanitized-name "/" ".")
        data           { :raw-name       name
                         :sanitized-name sanitized-name
                         :name           (project-name name)
                         :namespace      main-ns
                         :nested-dirs    (name-to-path main-ns)
                         :year           (year)
                         :module-id      module-id }]
    (main/info "Generating a project called" name "based on the 'amp' template.")
    (main/info "To see other templates (app, plugin, etc), try `lein help new`.")
    (main/info "Module id for this AMP is" module-id)
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
             "src/resource/alfresco/extension/templates/webscripts/{{module-id}}"
             "src/resource/alfresco/module/{{module-id}}"
             ["src/resource/alfresco/module/{{module-id}}/module-context.xml" (render "module-context.xml" data)]
             "src/resource/alfresco/module/{{module-id}}/context"
             "src/resource/licenses"

             "src/amp"
             ["src/amp/module.properties" (render "module.properties" data)]
             "src/resource/META-INF/resources"
             "src/resource/META-INF/resources/html/{{module-id}}"
             "src/resource/META-INF/resources/css/{{module-id}}"
             "src/resource/META-INF/resources/images/{{module-id}}"
             "src/resource/META-INF/resources/js/{{module-id}}")))
