(ns lambdaui.core
  (:require
    [compojure.core :refer [routes GET context POST]]
    [lambdaui.api-legacy :as new-api]
    [ring.middleware.json :as ring-json]
    [ring.middleware.params :refer [wrap-params]]
    [lambdacd.ui.api :as old-api]
    [lambdacd.ui.ui-page :as old-ui]
    [compojure.route :as route]
    [lambdaui.trigger :as runner]
    [lambdaui.config :as config])
  (:gen-class))

(defn pipeline-routes
  ([pipeline & {:keys [showNewBuildButton contextPath]
                :or   {showNewBuildButton false
                       contextPath        ""}}]
   (ring-json/wrap-json-response
     (wrap-params
       (routes
         (route/resources "/lambdaui" {:root "public"})
         (route/resources "/" {:root "public"})
         (GET "/lambdaui" [] (ring.util.response/redirect "lambdaui/index.html"))
         (GET "/lambdaui/config.js" [] (config/pipeline->config pipeline {:showNewBuildButton showNewBuildButton
                                                                          :path-prefix contextPath}))
         (context "/lambdaui/api" [] (new-api/api-routes pipeline))
         (POST "/lambdaui/api/triggerNew" request (do (runner/trigger-new-build pipeline request) {}))
         (GET "/" [] (old-ui/ui-page pipeline))
         (context "/api" []
           (old-api/rest-api pipeline)
           ))))))

(defn ui-for [pipeline & opts]
  (pipeline-routes pipeline opts))
