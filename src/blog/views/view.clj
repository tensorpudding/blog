(ns blog.views.view
  (:require [blog.models.posts :as posts]
            [blog.models.users :as users]
            [blog.views.notfound :as notfound]
            [blog.views.entry :as entry]
            [net.cgrand.enlive-html :as enlive]))

(enlive/deftemplate view-template "blog/views/view.html"
  [user entry]
  [:title] (enlive/content (str (:title entry) " by " user))
  [:div.entries] (enlive/content (entry/single-entry user entry)))

(defn display "Display view"
  [pid]
  (let [res (posts/get-post pid)]
    (if (empty? res)
      (notfound/display)
      (let [user (users/find-name (:uid res))]
        (view-template user res)))))
