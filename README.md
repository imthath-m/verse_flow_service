# Verse Flow Service

This is the backend service for the Verse Flow platform, built using Java Spring Boot (v3.2) and PostgreSQL.

## Local Database Setup

### Prerequisites
You need to have PostgreSQL installed on your machine.

### For Mac Users
1. Install PostgreSQL using Homebrew:
   ```bash
   brew install postgresql@16
   ```
2. Start the PostgreSQL service:
   ```bash
   brew services start postgresql@16
   ```
3. Create the database and necessary user:
   ```bash
   psql postgres
   ```
   *If you face an error, try `psql -U $(whoami) postgres` or ensure postgres process initialized properly.*
   
   Run the following commands inside `psql`:
   ```sql
   CREATE DATABASE verse_flow;
   CREATE USER postgres WITH PASSWORD 'password';
   ALTER ROLE postgres SET client_encoding TO 'utf8';
   ALTER ROLE postgres SET default_transaction_isolation TO 'read committed';
   ALTER ROLE postgres SET timezone TO 'UTC';
   GRANT ALL PRIVILEGES ON DATABASE verse_flow TO postgres;
   \q
   ```

### For Linux Users
1. Update apt repositories and install PostgreSQL (Ubuntu/Debian example):
   ```bash
   sudo apt update
   sudo apt install postgresql postgresql-contrib
   ```
2. Ensure the service is running:
   ```bash
   sudo systemctl start postgresql
   sudo systemctl enable postgresql
   ```
3. Create the database and start the shell using the default `postgres` user:
   ```bash
   sudo -u postgres psql
   ```
   Run the following commands inside `psql`:
   ```sql
   CREATE DATABASE verse_flow;
   ALTER USER postgres WITH PASSWORD 'password';
   ALTER ROLE postgres SET client_encoding TO 'utf8';
   ALTER ROLE postgres SET default_transaction_isolation TO 'read committed';
   ALTER ROLE postgres SET timezone TO 'UTC';
   GRANT ALL PRIVILEGES ON DATABASE verse_flow TO postgres;
   \q
   ```

## Running the Application
Once the database is set up and running, you can run the application directly using Maven wrapper.

```bash
./mvnw clean spring-boot:run
```

The database schema will automatically generate/update on startup due to the JPA `ddl-auto=update` configuration in `application.properties`.
