# AccessFlow

**Team 01 · Western Caspian University · Fall 2026**
Instructor: Elvin Qurbanov

## About

In most universities, access to internal systems is granted informally:
someone asks an administrator in a chat, gets access, and nobody remembers
it a year later. AccessFlow replaces this with a clear process. A user
requests access to a specific system and level, the reviewer responsible
for that system approves or rejects it, access expires on its own after the
requested date, and every step is recorded.

The project is a server-rendered web application built with Java 25 and
Spring Boot 4.1, with Spring Security for sign-in, Spring Data JPA for data
access, an H2 database stored in a file, and Thymeleaf templates. Exact
library versions are listed in `pom.xml`. The interface is in English.

## Getting started

Install JDK 25. Maven is not required, because the project ships with the
Maven Wrapper. Start the app from the project folder:

```bash
./mvnw spring-boot:run
```

On Windows use `mvnw.cmd spring-boot:run`. Then open http://localhost:8080.

On the first run the app creates its database in the `data` folder and adds
the demo accounts. Data survives restarts. For a clean start, stop the app
and delete the `data` folder.

To run the tests: `./mvnw test`.

## Demo accounts

All demo accounts use the password `demo123`.

- **admin** — administrator: manages the system catalog and sees all requests.
- **reviewer1** — reviewer: decides on requests for the systems assigned to them.
- **user1** — applicant: submits requests and sees only their own.

New users who register through the sign-up page always become applicants.

## Team

- **Rasul Huseynzade** — backend, frontend and integration: project setup,
  sign-in and registration, roles and access control, data model, business
  rules, pages.
- **Əbdül Zeynalov** — database design and the ER diagram.
- **Mehriban Orucova** — UI design and the screen sketches.

We test and check the final version together. Each member commits from their
own GitHub account, so the commit history shows who did what.

## Documentation

- [ER diagram](docs/er-diagram.png) — database tables and their relations.
- Screen sketches, planned before the pages were built:
  [sign in](docs/screen-1-sign-in.png),
  [systems](docs/screen-2-systems.png),
  [new request](docs/screen-3-request-form.png),
  [my requests](docs/screen-4-my-requests.png),
  [request details](docs/screen-5-request-details.png),
  [review panel](docs/screen-6-review-panel.png),
  [report](docs/screen-7-report.png).

## Code layout

The code follows the usual Spring layers, each in its own package under
`src/main/java/az/wcu/accessflow`:

- `web` — controllers and form classes; they receive requests and choose a page.
- `service` — business rules, such as who may approve which request.
- `repository` — database access through Spring Data JPA.
- `domain` — entities that map to database tables, and the user roles.
- `config` — security rules and demo data.

HTML templates are in `src/main/resources/templates`, documentation in `docs`.

## Security notes

- Passwords are hashed with BCrypt and never stored as plain text.
- The database is accessed only through JPA repositories, without SQL built
  from strings.
- Spring Security protects every form with a CSRF token.
- Thymeleaf escapes user input on output.
- Access to pages is decided on the server, not by hiding buttons.
- The sign-up form cannot create administrators or reviewers.

## Progress

**Week 1 (9–15 November).** Project setup, registration and sign-in with
roles and hashed passwords, database design, screen sketches.