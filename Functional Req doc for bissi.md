# 📄 Functional Requirements Document (FRD)

## Project Title: **SaathiFund** *(Suggested name – customizable)*

## Version: 1.1

## Prepared by: Bhaskar Panthri

## Date: \[2025-06-07]

---

## 1. 📘 Introduction

### 1.1 Purpose

This document defines the functional requirements for the **SaathiFund** application – a digital community-based micro-loan and savings platform inspired by the traditional “Bissi” model with key enhancements:

* Loan disbursal based on verified need and admin approval.
* Admin-managed savings committees (not lottery-based).
* Interest-earning system redistributed among members.

### 1.2 Scope

The application enables:

* Committee-based monthly financial commitments
* Need-based low-interest loan disbursal
* End-of-year interest earnings redistribution among members
* Admin-controlled group validation and operations

---

## 2. 🧑‍🧻‍🧒‍🧻 Actors and Roles

| Role       | Description                                                                  |
| ---------- | ---------------------------------------------------------------------------- |
| **Admin**  | Creates and manages savings committee, approves loans, oversees transactions |
| **Member** | Joins verified committees, contributes monthly, applies for and repays loans |
| **System** | Handles logic for payments, interest, validation, notifications, reports     |

---

## 3. 📋 Functional Requirements

### 3.1 User Registration and Authentication

* FR-1.1: The system shall allow new users to register via email/phone OTP verification.
* FR-1.2: Secure login with JWT token authentication.
* FR-1.3: Users must complete a profile with KYC (optional), bank details, and mobile number.

### 3.2 Committee Creation and Joining

* FR-2.1: Verified users may create a new committee (pending approval by system or super admin).

* FR-2.2: Committees shall define:

  * Monthly contribution amount
  * Maximum number of members
  * Interest rate(s) for loans
  * Maximum loan amount
  * Estimated yearly profit from interest

* FR-2.3: Members can explore multiple verified Bissi fund committees and request to join based on:

  * Description
  * Interest rates
  * Loan cap
  * Estimated annual return

* FR-2.4: Joining mechanisms:

  * Approval by admin
  * Invite code by member

* FR-2.5: A committee can have only one admin (creator).

### 3.3 Monthly Contributions

* FR-3.1: System tracks each member’s:

  * Contribution status
  * Interest earned (if applicable)
  * Loan repayment dues

* FR-3.2: Payments can be made:

  * Online (via UPI, bank)
  * Offline (manual entry by admin after F2F payment)

* FR-3.3: Contribution deadline is **10th day** of every month.

  * Post-deadline fines are auto-imposed.

* FR-3.4: Missed payments are marked, fined, and members are notified.

### 3.4 Loan Application

* FR-4.1: Loan applications accepted between **20th and end of each month**.

* FR-4.2: Form includes:

  * Amount requested
  * Loan purpose
  * Priority level
  * Supporting documents (optional)
  * Supporting guarantor members (backup payers in case of default)

* FR-4.3: Members cannot apply if they have outstanding EMI dues.

### 3.5 Loan Approval and Disbursement

* FR-5.1: Admin reviews applications and sets:

  * Approval or rejection
  * Interest rate (1%, 2%, etc.) on **remaining loan**
  * Repayment tenure
  * Minimum monthly repayment

* FR-5.2: Disbursement can be done via:

  * Bank transfer
  * Manual cash handover by admin (logged in system)

* FR-5.3: System auto-generates an EMI plan.

* FR-5.4: One active loan allowed per member.

### 3.6 Repayment

* FR-6.1: EMI payments auto-tracked monthly.
* FR-6.2: Late payments marked, optionally fined.
* FR-6.3: Early repayments allowed without penalty.

### 3.7 Admin Controls

* FR-7.1: Admin can view:

  * Group transactions
  * Member contributions
  * Loan applications & history

* FR-7.2: Admin can remove members with 2+ missed payments.

* FR-7.3: Admin can pause new loan applications for any month.

### 3.8 Notifications

* FR-8.1: Members receive alerts for:

  * Monthly contributions
  * EMI due
  * Loan decision

* FR-8.2: Delivered via app push, SMS, or email.

### 3.9 Dashboard and Reports

* FR-9.1: Member Dashboard:

  * Monthly contributions
  * Interest return
  * Loan status

* FR-9.2: Admin Dashboard:

  * Total funds
  * Interest earned
  * Loan disbursed & recoveries

### 3.10 Interest Redistribution

* FR-10.1: At the end of each financial year, total interest earned from all loans in a committee is equally distributed among active members.
* FR-10.2: Redistribution amount is shown in user dashboard and optionally added to final month’s contribution.

---

## 4. ⚙️ Non-Functional Requirements

| ID    | Requirement                                |
| ----- | ------------------------------------------ |
| NFR-1 | Support for 10,000+ concurrent users       |
| NFR-2 | Transaction speed < 3 seconds              |
| NFR-3 | Mobile-first responsive design             |
| NFR-4 | Data encryption (AES, HTTPS)               |
| NFR-5 | 99.5% uptime                               |
| NFR-6 | Transaction audit logs maintained securely |

---

## 5. 🔒 Security Requirements

* SR-1: RBAC (Role-Based Access Control)
* SR-2: OTP or MFA on financial actions
* SR-3: Encrypted storage for all personal/banking data
* SR-4: Admins restricted from taking loans in their own group

---

## 6. 🧪 Future Enhancements

* Peer-reviewed loan approval voting
* Rotating committee leadership
* Credit score-based interest rate tiers
* PDF export of statements
* Blockchain ledger for transaction transparency

---

Would you like a **technical architecture document** or **project structure in Spring Boot + Angular** next?
