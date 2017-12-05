(ns expense.index
  (:require [ragtime.jdbc :as jdbc]
            [ragtime.repl :as repl]))

(defn load-config []
  {:datastore (jdbc/sql-database "jdbc:postgresql://localhost:5432/clojure_test?user=postgres&password=postgres")
   :migrations (jdbc/load-resources "migrations")})

(defn migrate [] (repl/migrate (load-config)))
(defn rollback [] (repl/rollback (load-config)))
