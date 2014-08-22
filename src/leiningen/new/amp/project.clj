(defproject {{sanitized-name}} "0.1.0-SNAPSHOT"
  :title "FIXME: write title"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license { :name "Eclipse Public License"
             :url "http://www.eclipse.org/legal/epl-v10.html" }
  :min-lein-version "2.0.0"
  :profiles { :dev { :plugins [[lein-amp "0.4.0-SNAPSHOT"]] }
              :provided { :dependencies [
                                          [org.clojure/clojure "1.6.0" :scope "runtime"]
;                                          [org.clojars.lambdalf/lambdalf "2.0.0-SNAPSHOT" :scope "runtime"]   ; Not yet available on clojars...
                                        ] }}
  :source-paths    ["src/clojure"]
  :resource-paths  ["src/resource"]
  :amp-source-path "src/amp")
