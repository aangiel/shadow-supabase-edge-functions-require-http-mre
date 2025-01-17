# shadow-supabase-edge-functions-require-http-mre

### Steps to reproduce 

1. `git clone https://github.com/aangiel/shadow-supabase-edge-functions-require-http-mre`
2. `cd shadow-supabase-edge-functions-require-http-mre`
3. `npm install`
4. `npx shadow-cljs watch sb-functions`

```shell
curl -i \
    --location --request POST "http://127.0.0.1:54321/functions/v1/mre_example" \
    --header "Authorization: Bearer $(supabase status 2>&1 | grep "anon key" | awk -F': ' '{print $2}')" \
    --header "Content-Type: application/json"
```