# FRODO
Frodo is a simple wanderer which you can order to execute some action and expect a result.
The possible actions are:
- info - just to check if the app works
- talk - frodo says hello and his name 

This is just my private playground prepared to remind myself on how to develop dropwizard apps. 

##Sample Commands

- Info resource: `curl 'localhost:8080/' -v`
- Talk resource:
  - to get a simple hello from Frodo asking for your name: `curl 'localhost:8080/talk' -v`
  - to get a hello from Frodo with your name: `curl 'localhost:8080/talk?name=Kris' -v`
