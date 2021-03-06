(defproject lambdaui "1.2.1-SNAPSHOT"
  :description "LambdaCD-Plugin that provides a modern UI for your pipeline."
  :url "https://github.com/sroidl/lambda-ui"
  :license {:name "Apache License 2.0"
            :url  "http://www.apache.org/licenses/LICENSE-2.0"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [lambdacd "0.13.4"]
                 [compojure "1.6.0"]
                 [http-kit "2.3.0"]
                 [org.clojure/data.json "0.2.6"]
                 [org.slf4j/slf4j-simple "1.7.25"]
                 [trptcolin/versioneer "0.2.0"]]

  :test-paths ["test"]
  :scm {:dir ".."}
  :deploy-repositories [["snapshots" {:url "https://clojars.org/repo"
                                      :username [:gpg :env]
                                      :password [:gpg :env]}]
                        ["releases" {:url "https://clojars.org/repo"
                                     :username [:gpg :env]
                                     :password [:gpg :env]}]]
  :lein-release {:scm :git}
  :profiles {:dev {:dependencies [[lambdacd-git "0.4.1"]
                                  [com.gearswithingears/shrubbery "0.4.1"]
                                  [ring-cors "0.1.11"]
                                  [lambdacd-artifacts "0.2.2"]]
                   :aot          [lambdaui.testpipeline.core]
                   :main         lambdaui.testpipeline.core}})
