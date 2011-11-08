(defproject blog "1.0.0-SNAPSHOT"
  :description "Simplistic blog made with Noir, Enlive, Korma"
  :main blog.core
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [noir "1.2.1-SNAPSHOT"]
                 [enlive "1.0.0"]
                 [korma "0.2.0"]
                 [postgresql "9.1-901.jdbc4"]
                 [digest "1.3.0"]
                 [clj-time "0.3.2"]]
  :dev-dependencies [[swank-clojure "1.4.0-SNAPSHOT"]])