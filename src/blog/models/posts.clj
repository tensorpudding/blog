(ns blog.models.posts
  (:require blog.db [clj-time.core :as ctime] [clj-time.format :as format]
            [clj-time.coerce :as coerce])
  (:use korma.core))

(defentity posts
  (pk :pid)
  (database blog.db/db))

(defn sql-current-time "Current timestamp in SQL Timestamp format"
  [] (java.sql.Timestamp. ^long (coerce/to-long (ctime/now))))

(defn parse-sql-time "Parse SQL timestamp to a string"
  [sqltime] (.toString sqltime))

(defn new-post "Store new post in database"
  [user title body] (insert posts
                            (values {:uid user
                                     :time (sql-current-time)
                                     :title title
                                     :body body})))

(defn get-post "Get post by pid"
  [pid] (let [result (select posts
                              (where {:pid pid})
                              (limit 1))]
          (if (empty? result)
            result
            (let [post (first result)]
              (assoc post :time (parse-sql-time (:time post)))))))

(defn get-posts-by-user "Get all posts owned by uid"
  [uid]
  (let [result (select posts
                       (where {:uid uid})
                       (order :time :DESC))]
    (map (fn [x] (assoc x :time (parse-sql-time (:time x)))) result)))

(defn get-last-n-posts "Get the last n posts"
  [n]
  (let [results (select posts
                        (limit n)
                        (order :time :DESC))]
    (map (fn [x] (assoc x :time (parse-sql-time (:time x)))) results)))