(ns blog.routes
  (:require [noir.core :as noir]
            [blog.views.view :as view]
            [blog.views.user :as user]
            [blog.views.new :as new]
            [blog.views.post :as post]
            [blog.views.index :as index]))

(noir/defpage [:get ["/view/:pid" :pid #"\d+"]] {:keys [pid]}
  (view/display (Integer. pid)))

(noir/defpage [:get ["/user/:uid" :uid #"\d+"]] {:keys [uid]}
  (user/display (Integer. uid)))

(noir/defpage [:get "/new"] []
  (new/display))

(noir/defpage [:post "/new"] {:keys [title body]}
  (post/display title body))

(noir/defpage [:get "/"] []
  (index/display))