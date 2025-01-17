(ns mre-example.index
  (:require #_["@supabase/supabase-js"]))

(js/Deno.serve
  (fn []
      (js/Response. "Hello from Supabase Edge Functions!")))
