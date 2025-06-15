# 🏗️ Technical Architecture Document

## Project Title: **SaathiFund**

## Version: 1.0

## Prepared by: Bhaskar Panthri

## Date: 2025-06-07

---

## 1. 🎯 Objective

To define the high-level technical architecture of the **SaathiFund** application—a secure, scalable, community-based micro-lending and savings platform.

---

## 2. 🔧 Technology Stack

### 🖥️ Frontend:

* \*\*Framework: Reatjs / kmp for mob
* **UI Library:** shadcn/TailwindCSS
* **Authentication:** JWT or Oauth2
* **Deployment\:unknown**

### 🧠 Backend:

* **Framework:** Spring Boot (v3.x)
* **Build Tool:** Maven
* **Security:** Spring Security + JWT + Role-based Access Control
* **Persistence:** JPA/Hibernate
* **Validation:** Bean Validation (JSR-380)

### 🗃️ Database:

* **RDBMS:** PostgreSQL, SQLite
* **Schema:** Normalized with foreign key relations (Users, Committees, Loans, EMIs)
* **NoSQL (optional):** Redis for caching dashboards, recent transactions

### 🔄 Communication:

* **API Type:** RESTful JSON APIs
* **API Docs:** Swagger/OpenAPI 3.0

### ☁️ DevOps:

* **CI/CD:** GitHub Actions / Jenkins
* **Containerization:** Docker
* **Monitoring:** Spring Boot Actuator + Prometheus + Grafana
* **Hosting:** AWS / Azure / GCP

---

## 3. 📐 High-Level Architecture

```
[ React/Mob Frontend ]
       ↓ (HTTP Requests)
[ Spring Boot Backend ]
       ↓
[ PostgreSQL DB ] ←→ [ Redis (optional) ]
       ↓
[ Email/SMS Service ]
```

### Key Components:

* **Authentication Module**: Handles login, JWT issuance, role validation
* **Committee Management Module**: Group creation, join requests, rules validation
* **Contribution Service**: Monthly contribution logic, deadlines, fines
* **Loan Service**: Applications, approvals, EMI schedules
* **Admin Dashboard**: Group overview, control tools, loan approvals
* **Notification Service**: Sends contribution and EMI reminders via email/SMS
* **Interest Distribution Engine**: Year-end interest split among users

---

## 4. 🔐 Security Architecture

* JWT-based session management
* Admins restricted from loan self-approval
* All endpoints role-guarded (Member/Admin)
* AES encryption for PII in DB
* HTTPS enforced for all services

---

## 5. 🔄 Data Flow Overview

### New User Registration:

```
1. User submits form → Backend validates + stores → OTP sent → Token issued
```

### Monthly Contribution:

```
1. Scheduler triggers monthly deadline
2. System checks payment status
3. Fines added if unpaid
4. Dashboard updated
```

### Loan Application:

```
1. User submits loan form
2. Admin reviews + approves/denies
3. EMI schedule auto-generated
4. Loan amount disbursed
```

### Interest Distribution:

```
1. Year-end batch job sums loan interest
2. Amount equally split
3. Dashboards updated + payout options shown
```

---

## 6. 🧪 Testing Strategy

* **Unit Tests:** JUnit, Mockito
* **Integration Tests:** Spring Boot Test
* **E2E Tests:** Cypress / Playwright
* **API Testing:** Postman / Swagger UI

---

## 7. 🧱 Deployment Pipeline

### GitHub Actions CI/CD Flow:

```
Push to `main` →
Run Tests →
Build Docker Image →
Deploy to Dev Environment →
Notify Team
```

---

## 8. 🔮 Future Integrations

* Aadhaar-based KYC
* UPI auto-debit integration (Razorpay, Cashfree)
* Credit bureau integration
* Blockchain ledger for transparency

---

Would you like a **Database Schema Design** next?
