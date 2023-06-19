# micro-blogging
A simple micro blogging project

# Database setup
This application uses Postgres database, to setup the database please create user roles and database schema based on the initial-db-setup.sql.

# Application Setup

- Replace `mastodon.instanceUrl`, `mastodon.accessToken` and `twitter.auth.token` with the actual value
- Execute the database setup
- Run `mvn clean install`
- Run `mvn spring-boot:run`


#### Application will be started on localhost:8001, which shows the simple page where we show the old message from mastodon and twitter and also stream the real time message there.

