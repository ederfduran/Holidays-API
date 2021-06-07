### Brief

It's November, and everyone is planning their holiday vacation. But management is struggling! They need a solution to approve vacation requests while also ensuring that there are still enough employees in the office to achieve end-of-year goals.

Your task is to build one HTTP API that allows employees to make vacation requests, and another that provides managers with an overview of all vacation requests and allows them to decline or approve requests.

### Tasks

- Implement assignment using:
  - Language: Java
  - Framework: Spring
- There should be API routes that allow workers to:
  - See their requests
    - Filter by status (approved, pending, rejected)
  - See their number of remaining vacation days
  - Make a new request if they have not exhausted their total limit (30 per year)
- There should be API routes that allow managers to:
  - See an overview of all requests
    - Filter by pending and approved
  - See an overview for each individual employee
  - See an overview of overlapping requests
  - Process an individual request and either approve or reject it
- Write tests for your business logic

Each request should, at minimum, have the following signature:

```
{
  "id": ENTITY_ID,
  "author": WORKER_ID,
  "status": STATUS_OPTION, // may be one of: "approved", "rejected", "pending"
  "resolved_by": MANAGER_ID,
  "request_created_at": "2020-08-09T12:57:13.506Z",
  "vacation_start_date" "2020-08-24T00:00:00.000Z",
  "vacation_end_date" "2020-09-04T00:00:00.000Z",
}
```

You are expected to design any other required models and routes for your API.

### Evaluation Criteria

- Java best practices
- Completeness: Did you include all features?
- Correctness: Does the solution perform in a logical way?
- Maintainability: Is the solution written in a clean, maintainable way?
- Testing: Has the solution been adequately tested?
- Documentation: Is the API well-documented?

### CodeSubmit

Please organize, design, test, and document your code as if it were going into production - then push your changes to the master branch. After you have pushed your code, you must submit the assignment via the assignment page.

All the best and happy coding,

The Fetcher Team

### API Design

#### CRUD Employee

---

**POST /v1/employees**
Create Employee

---

---

**GET /v1/employees/:id (REQUIRED!)**
Get Employee information, including vacations days available

---

---

**PUT /v1/employees/:id**
Update Employee

---

---

**DELETE /v1/employees/:id**
Create Employee

---

#### CRUD Manager

---

**POST /v1/managers**
Create manager

---

---

**GET /v1/managers/:managerid**
Get manager information

---

---

**PUT /v1/managers/:managerid**
Update manager

---

---

**DELETE /v1/managers/:managerid**
Delete manager

---

#### CRUD Vacations Request

---

**POST /v1/employees/:id/vacationrequests (REQUIRED!)**
Create vacation request

---

---

**GET /v1/employees/:id/vacationrequests/:requestid**
Get vacation request by id

---

---

**PUT /v1/employees/:id/vacationrequests/:requestid (REQUIRED!)**
Update vacation request by id, Manager to process individual request

---

---

**DELETE /v1/employees/:id/vacationrequests/:requestid**
Delete vacation request by id

---

### Additional endpoints

---

**GET /v1/employees/:id/vacationrequests?status=<all | approved | pending | rejected> (REQUIRED!)**
Employee to get vacations request by employeeid, filter by status, default status=all

---

**GET /v1/vacationrequests?paginate=100&?status=<all | approved | pending>&overlapping=<true | false> (REQUIRED!)**
Manager to see available requests , status default all, overlapping default to false

---

### Data Model

The DB to use will be an SQL database in order to better represent the relationship between the different entities we'll going to have. It doesn't mean that it can't be donde with a noSQL database but with the information given an SQL data base is well suited. In a real world scenario we should consider other variables such us how much flexibility we need in our data model, or how we want to scale our system and ofcourse the complexity of the queries we'll need in the future.

![Entiti Model](/docs/entity_model.png)

- **Table employee**
  id
  name
  start_date
  vacation_days
  vacation_days_taken

- **Table manager**
  id
  name

- **Table vacation_request**
  id
  author
  status
  resolved_by
  request_created_at
  vacation_start_date
  vacation_end_date

### Assumptions

As part of the exercise it is assumed that:

- An employee does not have only one manager assigned
- Any manager can process the request of any Employee
- Manager can't request vacations
- No need to wait for a year to request vacations

### Dependencies

#### Project Lombok

Java library to get rid of boilerplate code. [project lombok](https://projectlombok.org/)

#### JPA

This is the ORM used for mapping entities into mysql db.
