(ns blog.views.post
  (require [blog.models.posts :as posts]
           [noir.response :as resp]))

(defn display "Redirect to posted page"
  [title body]
  (let [post (posts/new-post 1 title body)
        pid (:pid post)]
    (resp/redirect (str "/view/" pid))))