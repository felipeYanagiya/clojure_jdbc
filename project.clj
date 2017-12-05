(defproject expense "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [compojure "1.5.1"]
                 [org.clojure/java.jdbc "0.7.3"]
                 [hikari-cp "1.8.3"]
                 [org.postgresql/postgresql "42.1.4"]
                 [ragtime "0.7.2"]
                 [ring/ring-defaults "0.2.1"]]
  :plugins [[lein-ring "0.9.7"]
            [ragtime/ragtime.lein "0.3.7"]]
  :aliases {"migrate" ["run" "-m" "expense.index/migrate"]
            "rollback" ["run" "-m" "expense.index/rollback"]}
  :ring {:handler expense.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.0"]]}})
