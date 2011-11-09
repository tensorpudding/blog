(ns blog.views.entry
  (:require [net.cgrand.enlive-html :as enlive]))

(defn set-title "Sets the title link tag content and attributes"
  [entry]
  (let [pid (:pid entry)
        title (:title entry)]
    (comp (enlive/set-attr :href
                           (str "/view/" pid)
                           :id
                           (str "title" pid))
          (enlive/content title))))

(defn set-author "Sets the author link tag content and attributes"
  [user entry]
  (let [uid (:uid entry)
        pid (:pid entry)]
    (comp (enlive/set-attr :href
                           (str "/user/" uid)
                           :id
                           (str "author" pid))
          (enlive/content user))))

(defn set-date "Sets the date tag content and attributes"
  [entry]
  (let [pid (:pid entry)
        date (:time entry)]
    (comp (enlive/set-attr :id
                           (str "date" pid))
          (enlive/content date))))

(defn set-body "Sets the body tag content and attributes"
  [entry]
  (let [pid (:pid entry)
        body (:body entry)]
    (comp (enlive/set-attr :id
                           (str "body" pid))
          (enlive/content body))))


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
                        (set-title (nth entries i))

                        [:a.author]
                        (set-author user (nth entries i))

                        [:h3.date]
                        (set-date (nth entries i))

                        [:h4.body]
                        (set-body (nth entries i))))))

;; Snippet for generating a single entry for page
(enlive/defsnippet single-entry
  "blog/views/entry.html" [:div.entrylist]
  [user entry]

  [:a.title]
  (set-title entry)

  [:a.author]
  (set-author user entry)

  [:h3.date]
  (set-date entry)

  [:h4.body]
  (set-body entry))