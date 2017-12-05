(ns expense.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [clojure.java.jdbc :as jdbc]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

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
  (salsixa-command))

(defroutes app-routes
  (GET "/" [] (salsixa))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
