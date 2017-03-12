# FRODO
Frodo is a simple wanderer which you can order to execute some action and expect a result.
The possible actions are:
- info - just to check if the app works
- talk - frodo says hello and his name 
- inventory - ability to store objects
  - ring inventory - only a single ring exists, can't be taken unless with the `force` parameter, won't be shown unless the `friend` parameter exists

This is just my private playground prepared to remind myself on how to develop dropwizard apps. 

##Sample Commands

- Info resource: `curl 'localhost:8080/' -v`
- Talk resource:
  - to get a simple hello from Frodo asking for your name: `curl 'localhost:8080/talk' -v`
  - to get a hello from Frodo with your name: `curl 'localhost:8080/talk?name=Kris' -v`
- Inventory resource (guarded with http basic auth):
  - to get all elements in inventory: `curl 'localhost:8080/inventory' -v --user user:pass`
  - to save a single element in inventory: `curl 'localhost:8080/inventory' -v -XPOST -H 'Content-type: application/json' -d '{"name":"Lembas bread", "description":"some elvish shit"}' --user user:pass`
  - to get a single element from inventory: `curl 'localhost:8080/inventory/{id}' -v --user user:pass`
  - the one ring:
    - to give the ring to Frodo: `curl -v 'http://localhost:8080/inventory/ring' -XPUT -d '{"name": "One ring", "description": "One ring to rule them all and in darkness bind them" }' -H 'Content-type: application/json' --user user:pass`
    - to try to see the ring: `curl -v 'localhost:8080/inventory/ring' --user user:pass`
    - to see the ring when you're a friend: `curl -v 'localhost:8080/inventory/ring?friend=true' --user user:pass`
    - to try to get the ring from Frodo: `curl -v 'localhost:8080/inventory/ring' -XDELETE --user user:pass`
    - to get the ring from Frodo with brute force: `curl -v 'localhost:8080/inventory/ring?force=true' -XDELETE --user user:pass`
 
