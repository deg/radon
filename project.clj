(defproject
  com.degel/radon "0.1.0-SNAPSHOT"
  :description "Useful utilities for React Native in ClojureScript"
  :url "https://github.com/deg/radon"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/clojurescript "1.9.946"]
                 [re-frame "0.10.4"]
                 [reagent "0.7.0"]
                 [com.degel/iron "0.2.0"]]
  :plugins [[lein-cljsbuild "1.1.7"]
            [lein-doo "0.1.8"]]
  :cljsbuild
  {:builds
   [{:id "dev"
     :source-paths ["src"]
     :compiler {:pretty-print true}}
    {:id "test"
     :source-paths  ["src" "test"]
     :compiler {:main          radon.runner
                :output-to     "resources/public/js/compiled/test.js"
                :output-dir    "resources/public/js/compiled/test/out"
                :optimizations :none}}]})
