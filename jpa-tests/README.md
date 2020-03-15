# JPA tests

##### Simple JPA tests that can be used to quickly get feedback about queries.

**Problems:**

- We don't have types and we will learn at runtime if the query is correct
- We learn at runtime if we have incorrect syntax or typos
- Sometimes the documentation isn't enough and we have to check the real thing
- Checking if a query is implemented correctly is slow or requires a lot of preparation
- Not easy to find out the generated SQL

**Benefits:**

- Quick feedback if query works
- Catch silly/amateur errors such as typos or missing fields
- Problem isolation when you debug a database problem
- You learn how your repository queries behave so that you can mock them
- Write JPA integration tests for all the functions that you mock
so that we don't create mocks
- Easy to check if JPA throws exception or returns empty response
- Quick feedback to analyze a query
- Provides a "main" function to experiment

### DO

- Write integration tests for all your queries
- Use `@DataJpaTest` to test the queries to autoconfigure spring-data
- Make sure these test use only the database related configuration (e.g Hibernate and flyway)
- If the test setup is slow, check if manually starting the Hibernate Manager will fix the delay


// TODO
- use case for @Transactional
- Service exceptions and query timeouts
- commit and rollback
- common error cases
- add example using test containers