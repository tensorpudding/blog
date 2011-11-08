(ns blog.core
  (:require [noir.server :as server])
  (:use blog.routes))

(defn -main "Main method"
  [] (server/start 8080))