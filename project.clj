(defproject ip "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.5"]
                 [ring/ring-jetty-adapter "1.1.6"]]
  :plugins [[lein-ring "0.8.5"]]
  :min-lein-version "2.0.0"
  :ring {:handler ip.handler/app}
  :profiles
  {:dev {:dependencies [[ring-mock "0.1.5"]]}})
