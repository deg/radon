(ns radon.runner
    (:require [doo.runner :refer-macros [doo-tests]]
              [radon.core-test]))

(doo-tests 'radon.utils-test
           'radon.core-test)
