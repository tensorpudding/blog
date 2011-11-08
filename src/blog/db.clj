(ns blog.db
  (:use korma.db))

(defdb db (postgres {:db "blog"
                     :host "localhost"
                     :port "5432"
                     :user "blog"
                     :password "blog"}))