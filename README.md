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
