(ns blog.views.index
  (:require [blog.models.posts :as posts]
            [blog.views.entry :as entry]
            [net.cgrand.enlive-html :as enlive]))

(enlive/deftemplate index-template "blog/views/index.html"
  [entries]
  [:title] (enlive/content "Blog")
  [:div.entries] (enlive/content (entry/multiple-entries entries)))

(defn display "Display index view"
  []
  (let [entries (posts/get-last-n-posts 15)]
    (index-template entries)))