# fyp-msuic-platform-backend

## Start the server
```sh
./mvnw spring-boot:run
```

## remove cached git
```sh
git rm --cached e-learning-web-app-api

heroku git:remote -a e-learning-web-app-api
git add . && git commit -m "updated" && git push heroku master
```

## scale down
```sh
heroku ps:scale web=0
```
## scale up
```sh
heroku ps:scale web=1
```

## Retrieve your database URL by issuing the following command:
```sh
heroku config | grep CLEARDB_DATABASE_URL
```

## heroku logging
```sh
heroku logs --tail
```