(ns blog.models.users
  (:require blog.db digest)
  (:use blog.models.posts korma.core))

(defentity users
  (pk :uid)
  (database blog.db/db)
  (has-many posts))

(defn new-user "Store new user in database"
  [name password] (let [hash (digest/sha1 password)
                        res (insert users
                                    (values {:name name :password hash}))]
                    res))
                    

(defn validate-user "Validate login credentials"
  [name password] (let [res (select users (where {:name [= name]}))
                        hash (digest/sha1 password)]
                    (if (empty? res)
                      -2
                      (if (not (= hash ((res 0) :password)))
                        -1
                        ((res 0) :uid)))))

(defn del-user "Delete user"
  [name] (delete users (where {:name [= name]})))

(defn find-name "Find username corresponding to UID"
  [uid]
  (let [res (select users
                    (where {:uid uid}))]
    (if (empty? res)
      ""
      (:name (first res)))))