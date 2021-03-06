(ns lambdaui.trigger
  (:require [clojure.core.async :as async]
            [clojure.walk :refer [keywordize-keys]]
            [lambdacd.execution.core :as execution-core]))


;{:post [(v/is-not-nil-or-empty? (:branch (:global %)))
;        (v/is-not-nil-or-empty? (:revision (:global %)))
;        (v/is-valid-revision? (:revision (:global %)))]}

(defn trigger-new-build [{pipeline-def :pipeline-def context :context} {query-param :query-params}]
  (async/thread

    ;(println "Starting new build " )
    ;(when-let [git-commit (:git (keywordize-keys query-param))] (println "requested commit was" git-commit))
    (execution-core/run-pipeline pipeline-def context)
    ))
