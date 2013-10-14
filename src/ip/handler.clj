(ns ip.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [clojure.string :as string]
            [ring.adapter.jetty :as jetty]))

(defn- format-header [[header-name value]]
  (format "%s: %s" (string/capitalize header-name) value))

(defn headers [request]
  (string/join "  <br/>\n" (map format-header (:headers request))))

(defn ip [request]
  (or
    (get (:headers request) "x-forwarded-for")
    (:remote-addr request)))

(defn full-request [request]
  (str request))

(defroutes app-routes
  (GET "/" [] ip)
  (GET "/headers" [] headers)
  (GET "/request" [] full-request)
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))

(defn -main [& [port]]
  (let [port (Integer. (or port 3000))]
    (jetty/run-jetty app {:port port :join? false})))

(defn -main []
  (let [port (Integer/parseInt (or (System/getenv "PORT") "8080"))]
    (jetty/run-jetty app {:port port :join? false})))
