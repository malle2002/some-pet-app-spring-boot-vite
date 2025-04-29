## Step 1
  - ### Download ZIP, unzip and open the project from intellij and/or open terminal

  - ## start the db container from docker-compose file, or docker-compose up -d db

  - ## cd api  -> mvn clean package

  - ## docker-compose up -d 

## Step 2
### connect to http://localhost:5052/  use email: admin@admin.com and pw: admin to login to pgAdmin 4. 
Create Server <br/>
  host: (172.27.0.2) or get it from docker network ls -> docker network inspect <network_id or name> -> pet_postgres <br/>
  post: 5432<br/>
  username: postgres<br/>
  password: postgres<br/>

Test app on localhost:3001
