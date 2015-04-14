(ns shartfinder-ui.service-placeholders
  (:require [shartfinder-ui.common :refer :all]
            [taoensso.carmine :as car :refer (wcar)]
            [cheshire.core :refer [generate-string parse-string]]))

(defn- handle-add-combatant-request [context]
  (println "in service-placeholders handle-add-combatant-request")
  (wcar* (car/publish (:combatant-added channels)
                      (generate-string context))))

(defonce listener
  (car/with-new-pubsub-listener (:spec server-connection)
    {(:add-combatant-command channels) (handle-pubsub-subscribe handle-add-combatant-request)}

    (car/subscribe (:add-combatant-command channels))))
