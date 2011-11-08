(ns blog.views.new
  (:require [blog.models.users :as users]
            [blog.models.posts :as posts]
            [net.cgrand.enlive-html :as enlive]))

(enlive/deftemplate new-template "blog/views/new.html"
  [])

(defn display "Display new"
  []
  (new-template))
            