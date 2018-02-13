;;; Author: David Goldfarb (deg@degel.com)
;;; Copyright (c) 2018, David Goldfarb

(ns radon.core
  #?(:cljs (:require-macros [radon.core :refer [def-native-components]]))
  (:require
   [clojure.spec.alpha :as s]
   [oops.core :as oops]
   [re-frame.loggers :refer [console]]
   [reagent.core :as r]))


#?(:cljs
   (do
     (defn adapt-class
       "Adapt React class for use as a Reagent component."
       [class]
       (if class
         (r/adapt-react-class class)
         (console :error "React class " class " not found.")))

     (defn get-class
       "Extract React class from JavaScript module and adapt as Reagent component."
       [module class-name]
       (adapt-class (oops/oget+ module class-name)))))

#?(:clj
   (defmacro def-native-components
     "Extract one or more React classes from a JavaScript module. Adapt them as Reagent components and name them.
      Usage:
      `(get-components \"native-base\" [Body Button {:name ListNB :js-name \"List\"}])`

      [TODO] Weird bug that the js/require here seems to crash figwheel, unless the same module was previously
      require'd from .cljs. If this bug is real, I'll probably have to remove the require from this macro.
      "
     [module-name names-and-classes]
     (let [module (gensym "module-")]
       `(let [~module (js/require ~module-name)]
          ~@(map (fn [name]
                   (if (map? name)
                     (let [{:keys [name js-name]} name]
                       `(def ~name (get-class ~module ~js-name)))
                     `(def ~name (get-class ~module ~(str name)))))
                   names-and-classes)))))
