(ns blog.views.user
  (:require [blog.models.posts :as posts]
            [blog.models.users :as users]
            [blog.views.notfound :as notfound]
            [blog.views.entry :as entry]
            [net.cgrand.enlive-html :as enlive]))

(enlive/deftemplate user-template "blog/views/user.html"
  [user entries]
  [:title] (enlive/content (str "Posts by " user))
  [:h1.username] (enlive/content user)
  [:div.entries] (enlive/content (entry/user-entries user entries)))

(defn display "Display user"
  [uid]
  (let [user (users/find-name uid)]
    (if (= "" user)
      (notfound/display)
      (let [res (posts/get-posts-by-user uid)]
        (user-template user res)))))
