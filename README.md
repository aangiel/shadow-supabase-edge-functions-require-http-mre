# shadow-supabase-edge-functions-require-http-mre

> [!TIP]
> You don't have to run Supabase locally.

### Steps to reproduce 

```shell
git clone https://github.com/aangiel/shadow-supabase-edge-functions-require-http-mre
cd shadow-supabase-edge-functions-require-http-mre
npm install
npx shadow-cljs watch sb-functions
open http://localhost:9630/build/sb-functions
```

* (optional) `open http://localhost:9630/build/sb-functions` 

### Result

For now everything is ok.

But when you uncomment the only `:require` in file `supabase/functions/mre_example/index.cljs`:

```clojure
(ns mre-example.index
  (:require ["@supabase/supabase-js"]))

(js/Deno.serve
  (fn []
      (js/Response. "Hello from Supabase Edge Function!")))
```

Shadow throws error:
```
X Compilation failed.

The required JS dependency "http" is not available, it was required by "node_modules/@supabase/node-fetch/lib/index.js".

Dependency Trace:
	mre_example/index.cljs
	node_modules/@supabase/supabase-js/dist/main/index.js
	node_modules/@supabase/supabase-js/dist/main/SupabaseClient.js
	node_modules/@supabase/functions-js/dist/main/index.js
	node_modules/@supabase/functions-js/dist/main/FunctionsClient.js
	node_modules/@supabase/functions-js/dist/main/helper.js
	node_modules/@supabase/node-fetch/lib/index.js

Searched for npm packages in:
	/Users/arturangiel/projects/merlin/shadow-supabase-edge-functions-require-http-mre/node_modules
http is part of the node-libs-browser polyfill package to provide node-native package support
for none-node builds. You should install shadow-cljs in your project to provide that dependency.

	npm install --save-dev shadow-cljs

See: https://shadow-cljs.github.io/docs/UsersGuide.html#project-install

```

I've tried `npm install http`, `npm install node-libs-browser`, but it didn't help.

The same problem was also with `stream`, but `npm install stream` helped.

### If you want to check with Supabase

* You need to have Docker installed

```shell
npm install supabase
npx supabase start

curl -i \
    --location --request POST "http://127.0.0.1:54321/functions/v1/mre_example" \
    --header "Authorization: Bearer $(supabase status 2>&1 | grep "anon key" | awk -F': ' '{print $2}')" \
    --header "Content-Type: application/json"
```