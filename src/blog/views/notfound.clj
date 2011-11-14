(ns blog.views.notfound
  (:require [noir.response :as response]
            [net.cgrand.enlive-html :as enlive]))

(enlive/deftemplate notfound-template "blog/views/notfound.html"
  [])

(defn display "Display 404 page"
  []
  (response/status 404 (notfound-template)))