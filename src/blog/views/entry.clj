(ns blog.views.entry
  (:require [net.cgrand.enlive-html :as enlive]))


;; Snippet for generating lists of entries from one user
(enlive/defsnippet user-entries
  "blog/views/entry.html" [:div.entrylist]
  [user entries]

  [:div.entry]
  (let [c (count entries)]
    (if (= c 0)
      identity
      (enlive/clone-for [i (range c)]
                        
                        [:a.title]

                        (let [pid (:pid (nth entries i))
                              title (:title (nth entries i))]
                          (comp (enlive/set-attr :href
                                                 (str "/view/" pid)
                                                 :id
                                                 (str "title" pid))
                                (enlive/content title)))

                        [:a.author]
                        (let [uid (:uid (nth entries i))
                              pid (:pid (nth entries i))]
                          (comp (enlive/set-attr :href
                                                 (str "/user/" uid)
                                                 :id
                                                 (str "author" pid))
                                (enlive/content user)))

                        [:h3.date]
                        (let [pid (:pid (nth entries i))
                              date (:time (nth entries i))]
                          (comp (enlive/set-attr :id
                                                 (str "date" pid))
                                (enlive/content date)))

                        [:h4.body]
                        (let [pid (:pid (nth entries i))
                              body (:body (nth entries i))]
                          (comp (enlive/set-attr :id
                                                 (str "body" pid))
                                (enlive/content body)))))))

(enlive/defsnippet single-entry
  "blog/views/entry.html" [:div.entrylist]
  [user entry]

  [:a.title]
  (let [pid (:pid entry)
        title (:title entry)]
    (comp (enlive/set-attr :href
                           (str "/view/" pid)
                           :id
                           (str "title" pid))
          (enlive/content title)))

  [:a.author]
  (let [uid (:uid entry)
        pid (:pid entry)]
    (comp (enlive/set-attr :href
                           (str "/user/" uid)
                           :id
                           (str "author" pid))
          (enlive/content user)))
  [:h3.date]
  (let [pid (:pid entry)
        date (:time entry)]
    (comp (enlive/set-attr :id
                           (str "date" pid))
          (enlive/content date)))
  [:h4.body]
  (let [pid (:pid entry)
        body (:body entry)]
    (comp (enlive/set-attr :id
                           (str "body" pid))
          (enlive/content body))))
