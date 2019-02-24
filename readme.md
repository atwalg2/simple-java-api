# Simple Java Api

# Technology
`Language: Java`  
`Framework: Springboot`  
`ORM: Hibernate`  
`DB: Postgresql` 

## Configuration
```
  > application.properties
  ---------------------------
  ## Spring DATASOURCE (DataSourceAutoConfiguration & DataSourceProperties)
  spring.datasource.url=jdbc:postgresql://localhost:5432/<dbName>
  spring.datasource.username= uname
  spring.datasource.password= pass

  # The SQL dialect makes Hibernate generate better SQL for the chosen database
  spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.PostgreSQLDialect

  # Hibernate ddl auto (create, create-drop, validate, update)
  spring.jpa.hibernate.ddl-auto = update
```

## Running

## Tests

## External Dependencies
