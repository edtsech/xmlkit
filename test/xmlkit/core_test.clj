(ns xmlkit.core-test
  (:use clojure.test
        xmlkit.core))

(def el {:content [{:tag :b}
                    {:tag :a
                     :content [{:tag :d
                                :content [{:tag :e
                                           :content ["secret"]}]}]}
                    {:tag :c
                     :content ["abc"]}]})

(deftest find-by-tag-test
  (is (= (find-by-tag :c el)
         {:tag :c :content ["abc"]})))

(deftest find-el-test
  (is (= (find-el el :a :d :e)
         {:tag :e :content ["secret"]}))

  (is (= (:tag (find-el el :a))
         :a)))

(deftest find-el->>test
         (is (= (find-el->> el [:a :d] :content first :tag)
                :e)))

(deftest find-el->>test
         (is (= (find-el->> el [:a] :content first :tag))
                :e))

(deftest text-test
  (is (= (text (find-by-tag :a el))
         "secret")))

