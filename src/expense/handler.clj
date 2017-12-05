(ns expense.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [clojure.java.jdbc :as jdbc]
            [hikari-cp.core :refer :all]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(def datasource-options {:auto-commit        true
                         :read-only          false
                         :connection-timeout 30000
                         :validation-timeout 5000
                         :idle-timeout       600000
                         :max-lifetime       1800000
                         :minimum-idle       10
                         :maximum-pool-size  10
                         :pool-name          "db-salsixa-pool"
                         :adapter            "postgresql"
                         :username           "postgres"
                         :password           "postgres"
                         :database-name      "clojure_test"
                         :server-name        "localhost"
                         :port-number        5432
                         :register-mbeans    false})

(def datasource
  (make-datasource datasource-options))

(def db-spec
  {:dbtype "postgresql"
   :dbname "clojure_test"
   :user "postgres"
   :password "postgres"
   })

(defn salsixa-table [] (jdbc/query :salsixa
                                          [[:name "varchar(32)"]]))

(defn salsixa-command [] (jdbc/db-do-commands db-spec
                                              [(salsixa-table)]))

(defn salsixa []
  (jdbc/with-db-connection [conn {:datasource datasource}]
    (let [rows (jdbc/query conn "SELECT * FROM salsixa")]
      (println rows)))
  (close-datasource datasource))

(defroutes app-routes
  (GET "/" [] (salsixa))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
