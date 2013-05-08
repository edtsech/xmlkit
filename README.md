# xmlkit

Few helper functions for parsing XML files in Clojure.

## Installation

```clj
[org.clojars.edtsech/xmlkit "0.1.2"]
```

## Usage

```clj
(def el {:tag :x
         :content [{:tag :b}
                    {:tag :a
                     :content [{:tag :d
                                :content [{:tag :e
                                           :content ["secret"]}]}]}
                    {:tag :c}]})

(find-by-tag :c el)
;; => {:tag :c :content ["abc"]}

(text (find-by-tag :a el))
;; => "secret"

(find-el el :a :d :e)
;; => {:tag :e :content ["secret"]}

(find-el->> el [:a :d :e] text)
;; => "secret"

(tag? (find-by-tag :a el))
;; => true
```

## License

Copyright © 2013 edtsech@gmail.com

Distributed under the Eclipse Public License, the same as Clojure.
