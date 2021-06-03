# db-management

Client management web app

### To launch an app with docker:
* clone or download
* run boot-app.bat
* go to [http://localhost:8080/](http://localhost:8080/)
* or access rest api [http://localhost:8080/api/clients](http://localhost:8080/api/clients)

You can then clear these images with clear.bat

## Rest api
Method | Desc | url
------------ | ------------ | -------------
(GET) | show all clients | [/api/clients](http://localhost:8080/api/clients)
(GET) | find client by id | [/api/clients/1](http://localhost:8080/api/clients/1)
(DELETE) | delete all records | [/api/clients](http://localhost:8080/api/clients)
(DELETE) | delete client by id | [/api/clients/1](http://localhost:8080/api/clients/1)
(PUT) | update client | [/api/clients](http://localhost:8080/api/clients)
(POST) | add new client through request body | [/api/clients](http://localhost:8080/api/clients)
(POST) | add 5 random clients | [/api/clients/rand/5](http://localhost:8080/api/clients/rand/5)
