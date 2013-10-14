(ns ip.test.handler
  (:use clojure.test
        ring.mock.request  
        ip.handler))

(deftest test-app
  (testing "ip"
    (let [response (app (request :get "/"))]
      (is (= (:status response) 200))
      (is (= (:body response) "localhost"))))

  (testing "headers"
    (let [response (app (request :get "/headers"))]
      (is (= (:status response) 200))
      (is (re-find #"Host: localhost" (:body response)))))
  
  (testing "not-found route"
    (let [response (app (request :get "/invalid"))]
      (is (= (:status response) 404)))))
