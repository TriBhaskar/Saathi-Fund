-- ================================================================
-- SaathiFund Database Schema Design - CORRECTED VERSION
-- Version: 1.2
-- Database: PostgreSQL
-- Author: Based on FRD by Bhaskar Panthri
-- Date: 2025-06-14
-- ================================================================

-- ================================================================
-- SEQUENCES (Fixed duplicates)
-- ================================================================
CREATE SEQUENCE seq_saathifund_user_id
    INCREMENT 1
    START 1
    MINVALUE 1
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE seq_committee_id
    INCREMENT 1
    START 1
    MINVALUE 1
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE seq_membership_id
    INCREMENT 1
    START 1
    MINVALUE 1
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE seq_contribution_id
    INCREMENT 1
    START 1
    MINVALUE 1
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE seq_loan_id
    INCREMENT 1
    START 1
    MINVALUE 1
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE seq_guarantor_id
    INCREMENT 1
    START 1
    MINVALUE 1
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE seq_emi_id
    INCREMENT 1
    START 1
    MINVALUE 1
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE seq_transaction_id
    INCREMENT 1
    START 1
    MINVALUE 1
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE seq_distribution_id
    INCREMENT 1
    START 1
    MINVALUE 1
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE seq_notification_id
    INCREMENT 1
    START 1
    MINVALUE 1
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE seq_audit_id
    INCREMENT 1
    START 1
    MINVALUE 1
    NO MAXVALUE
    CACHE 1;

-- ================================================================
-- ENUMS (Fixed and organized)
-- ================================================================
CREATE TYPE kycstatusenum AS ENUM ('PENDING', 'VERIFIED', 'REJECTED');
CREATE TYPE committeestatusenum AS ENUM ('PENDING', 'ACTIVE', 'SUSPENDED', 'CLOSED');
CREATE TYPE membershipstatusenum AS ENUM ('PENDING', 'ACTIVE', 'SUSPENDED', 'REMOVED');
CREATE TYPE memberroleenum AS ENUM ('ADMIN', 'MEMBER');
CREATE TYPE paymentstatusenum AS ENUM ('PENDING', 'PAID', 'PARTIAL', 'OVERDUE');
CREATE TYPE paymentmethodenum AS ENUM ('ONLINE', 'OFFLINE', 'UPI', 'BANK_TRANSFER');
CREATE TYPE prioritylevelenum AS ENUM ('LOW', 'MEDIUM', 'HIGH', 'URGENT');
CREATE TYPE loanstatusenum AS ENUM ('PENDING', 'APPROVED', 'REJECTED', 'DISBURSED', 'ACTIVE', 'COMPLETED', 'DEFAULTED');
CREATE TYPE disbursementmethodenum AS ENUM ('BANK_TRANSFER', 'CASH', 'UPI');
CREATE TYPE guarantorstatusenum AS ENUM ('ACTIVE', 'RELEASED', 'LIABLE');
CREATE TYPE transactiontypeenum AS ENUM (
    'CONTRIBUTION_PAYMENT', 'LOAN_DISBURSEMENT', 'EMI_PAYMENT',
    'FINE_PAYMENT', 'INTEREST_DISTRIBUTION', 'REFUND'
);
CREATE TYPE transactionstatusenum AS ENUM ('PENDING', 'COMPLETED', 'FAILED', 'CANCELLED');
CREATE TYPE distributionstatusenum AS ENUM ('CALCULATED', 'DISTRIBUTED', 'CANCELLED');
CREATE TYPE distributionmethodenum AS ENUM ('CREDIT_TO_ACCOUNT', 'CASH', 'NEXT_CONTRIBUTION_ADJUSTMENT');
CREATE TYPE memberinterestdistributionstatusenum AS ENUM ('PENDING', 'COMPLETED', 'FAILED');
CREATE TYPE notificationtypeenum AS ENUM (
    'CONTRIBUTION_DUE', 'EMI_DUE', 'LOAN_APPROVED', 'LOAN_REJECTED',
    'PAYMENT_RECEIVED', 'FINE_IMPOSED', 'INTEREST_DISTRIBUTED',
    'COMMITTEE_UPDATE', 'SYSTEM_ALERT'
);
CREATE TYPE notificationstatusenum AS ENUM ('PENDING', 'SENT', 'DELIVERED', 'FAILED');
CREATE TYPE settingstypeenum AS ENUM ('STRING', 'INTEGER', 'DECIMAL', 'BOOLEAN', 'JSON');

-- ================================================================
-- 1. SAATHIFUND_USERS TABLE
-- ================================================================
CREATE TABLE saathifund_users (
    user_id BIGINT DEFAULT nextval('seq_saathifund_user_id') PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    phone_number VARCHAR(15) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    user_name VARCHAR(100) NOT NULL UNIQUE,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    date_of_birth DATE,
    address TEXT,
    city VARCHAR(100),
    state VARCHAR(100),
    pin_code VARCHAR(10),

    -- KYC Information
    aadhaar_number VARCHAR(12) UNIQUE,
    pan_number VARCHAR(10) UNIQUE,
    kyc_status kycstatusenum NOT NULL DEFAULT 'PENDING',
    kyc_verified_at TIMESTAMP,

    -- Bank Details
    bank_account_number VARCHAR(50),
    bank_ifsc_code VARCHAR(15),
    bank_name VARCHAR(100),
    account_holder_name VARCHAR(100),

    -- System fields
    is_active BOOLEAN DEFAULT true,
    email_verified BOOLEAN DEFAULT false,
    phone_verified BOOLEAN DEFAULT false,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_login_at TIMESTAMP
);

-- ================================================================
-- 2. COMMITTEES TABLE
-- ================================================================
CREATE TABLE committees (
    committee_id BIGINT DEFAULT nextval('seq_committee_id') PRIMARY KEY,
    admin_user_id BIGINT NOT NULL REFERENCES saathifund_users(user_id),
    committee_name VARCHAR(200) NOT NULL,
    description TEXT,

    -- Financial Configuration
    monthly_contribution_amount DECIMAL(12,2) NOT NULL CHECK (monthly_contribution_amount > 0),
    max_members INTEGER NOT NULL CHECK (max_members > 0),
    current_member_count INTEGER DEFAULT 0,

    -- Loan Configuration
    max_loan_amount DECIMAL(12,2) NOT NULL CHECK (max_loan_amount > 0),
    min_interest_rate DECIMAL(5,2) DEFAULT 1.00 CHECK (min_interest_rate >= 0),
    max_interest_rate DECIMAL(5,2) DEFAULT 3.00 CHECK (max_interest_rate >= min_interest_rate),
    max_loan_tenure_months INTEGER DEFAULT 12 CHECK (max_loan_tenure_months > 0),

    -- Settings
    late_payment_fine_amount DECIMAL(10,2) DEFAULT 0,
    late_payment_fine_percentage DECIMAL(5,2) DEFAULT 0,
    invite_code VARCHAR(20) UNIQUE,

    -- Status and Control
    committee_status committeestatusenum NOT NULL DEFAULT 'ACTIVE',
    loan_applications_paused BOOLEAN DEFAULT false,

    -- Financial Year
    financial_year_start_month INTEGER DEFAULT 4 CHECK (financial_year_start_month BETWEEN 1 AND 12),

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ================================================================
-- 3. COMMITTEE MEMBERS TABLE
-- ================================================================
CREATE TABLE committee_members (
    membership_id BIGINT DEFAULT nextval('seq_membership_id') PRIMARY KEY,
    committee_id BIGINT NOT NULL REFERENCES committees(committee_id),
    user_id BIGINT NOT NULL REFERENCES saathifund_users(user_id),

    -- Membership Details
    membership_status membershipstatusenum NOT NULL DEFAULT 'PENDING',
    join_request_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    approved_date TIMESTAMP,
    approved_by BIGINT REFERENCES saathifund_users(user_id),

    -- Member Role
    member_role memberroleenum NOT NULL DEFAULT 'MEMBER',

    -- Financial Tracking
    total_contributions_paid DECIMAL(12,2) DEFAULT 0,
    total_interest_earned DECIMAL(12,2) DEFAULT 0,
    missed_payments_count INTEGER DEFAULT 0,
    current_fine_amount DECIMAL(10,2) DEFAULT 0,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    UNIQUE(committee_id, user_id)
);

-- ================================================================
-- 4. MONTHLY CONTRIBUTIONS TABLE
-- ================================================================
CREATE TABLE monthly_contributions (
    contribution_id BIGINT DEFAULT nextval('seq_contribution_id') PRIMARY KEY,
    committee_id BIGINT NOT NULL REFERENCES committees(committee_id),
    user_id BIGINT NOT NULL REFERENCES saathifund_users(user_id),

    -- Contribution Period
    contribution_year INTEGER NOT NULL,
    contribution_month INTEGER NOT NULL CHECK (contribution_month BETWEEN 1 AND 12),

    -- Financial Details
    expected_amount DECIMAL(12,2) NOT NULL,
    paid_amount DECIMAL(12,2) DEFAULT 0,
    fine_amount DECIMAL(10,2) DEFAULT 0,

    -- Payment Details
    payment_status paymentstatusenum NOT NULL DEFAULT 'PENDING',
    payment_method paymentmethodenum,
    payment_date TIMESTAMP,
    payment_reference VARCHAR(100),

    -- Deadlines
    due_date DATE NOT NULL,
    grace_period_end_date DATE,

    -- System fields
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    UNIQUE(committee_id, user_id, contribution_year, contribution_month)
);

-- ================================================================
-- 5. LOANS TABLE
-- ================================================================
CREATE TABLE loans (
    loan_id BIGINT DEFAULT nextval('seq_loan_id') PRIMARY KEY,
    committee_id BIGINT NOT NULL REFERENCES committees(committee_id),
    borrower_user_id BIGINT NOT NULL REFERENCES saathifund_users(user_id),

    -- Loan Application Details
    application_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    loan_purpose TEXT NOT NULL,
    requested_amount DECIMAL(12,2) NOT NULL CHECK (requested_amount > 0),
    priority_level prioritylevelenum NOT NULL,
    supporting_documents TEXT[],

    -- Approval Details
    approved_amount DECIMAL(12,2),
    interest_rate DECIMAL(5,2),
    tenure_months INTEGER,
    minimum_monthly_payment DECIMAL(12,2),

    -- Loan Status
    loan_status loanstatusenum NOT NULL DEFAULT 'PENDING',

    -- Administrative Actions
    reviewed_by BIGINT REFERENCES saathifund_users(user_id),
    reviewed_date TIMESTAMP,
    approval_comments TEXT,
    rejection_reason TEXT,

    -- Disbursement Details
    disbursement_method disbursementmethodenum,
    disbursement_date TIMESTAMP,
    disbursement_reference VARCHAR(100),

    -- Repayment Tracking
    total_repaid_amount DECIMAL(12,2) DEFAULT 0,
    outstanding_principal DECIMAL(12,2) DEFAULT 0,
    outstanding_interest DECIMAL(12,2) DEFAULT 0,
    total_interest_paid DECIMAL(12,2) DEFAULT 0,

    -- Loan Completion
    loan_completion_date TIMESTAMP,
    early_closure BOOLEAN DEFAULT false,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ================================================================
-- 6. LOAN GUARANTORS TABLE (Fixed)
-- ================================================================
CREATE TABLE loan_guarantors (
    guarantor_id BIGINT DEFAULT nextval('seq_guarantor_id') PRIMARY KEY,
    loan_id BIGINT NOT NULL REFERENCES loans(loan_id),
    guarantor_user_id BIGINT NOT NULL REFERENCES saathifund_users(user_id),

    -- Guarantor Details
    guarantee_amount DECIMAL(12,2),
    guarantor_status guarantorstatusenum NOT NULL DEFAULT 'ACTIVE',
    guarantee_start_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    guarantee_end_date TIMESTAMP,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    UNIQUE(loan_id, guarantor_user_id)
);

-- ================================================================
-- 7. EMI SCHEDULE TABLE
-- ================================================================
CREATE TABLE emi_schedule (
    emi_id BIGINT DEFAULT nextval('seq_emi_id') PRIMARY KEY,
    loan_id BIGINT NOT NULL REFERENCES loans(loan_id),

    -- EMI Details
    emi_number INTEGER NOT NULL,
    due_date DATE NOT NULL,
    principal_amount DECIMAL(12,2) NOT NULL,
    interest_amount DECIMAL(12,2) NOT NULL,
    total_emi_amount DECIMAL(12,2) NOT NULL,

    -- Payment Status
    payment_status paymentstatusenum NOT NULL DEFAULT 'PENDING',
    paid_amount DECIMAL(12,2) DEFAULT 0,
    payment_date TIMESTAMP,
    payment_method VARCHAR(20),
    payment_reference VARCHAR(100),

    -- Late Payment
    late_payment_fine DECIMAL(10,2) DEFAULT 0,
    days_overdue INTEGER DEFAULT 0,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    UNIQUE(loan_id, emi_number)
);

-- ================================================================
-- 8. TRANSACTIONS TABLE (Audit Trail)
-- ================================================================
CREATE TABLE transactions (
    transaction_id BIGINT DEFAULT nextval('seq_transaction_id') PRIMARY KEY,
    committee_id BIGINT NOT NULL REFERENCES committees(committee_id),
    user_id BIGINT NOT NULL REFERENCES saathifund_users(user_id),

    -- Transaction Details
    transaction_type transactiontypeenum NOT NULL,
    amount DECIMAL(12,2) NOT NULL,
    transaction_status transactionstatusenum NOT NULL DEFAULT 'COMPLETED',

    -- Reference Information
    reference_id BIGINT,
    reference_type VARCHAR(50),

    -- Payment Details
    payment_method VARCHAR(50),
    payment_gateway VARCHAR(50),
    gateway_transaction_id VARCHAR(100),
    gateway_response TEXT,

    -- Metadata
    description TEXT,
    notes TEXT,
    processed_by BIGINT REFERENCES saathifund_users(user_id),

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ================================================================
-- 9. INTEREST DISTRIBUTIONS TABLE (Fixed)
-- ================================================================
CREATE TABLE interest_distributions (
    distribution_id BIGINT DEFAULT nextval('seq_distribution_id') PRIMARY KEY,
    committee_id BIGINT NOT NULL REFERENCES committees(committee_id),

    -- Distribution Period
    financial_year INTEGER NOT NULL,
    distribution_date DATE NOT NULL,

    -- Financial Summary
    total_interest_collected DECIMAL(12,2) NOT NULL,
    total_members_eligible INTEGER NOT NULL,
    per_member_distribution DECIMAL(12,2) NOT NULL,

    -- Status
    distribution_status distributionstatusenum NOT NULL DEFAULT 'CALCULATED',

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    processed_at TIMESTAMP
);

-- ================================================================
-- 10. MEMBER INTEREST DISTRIBUTIONS TABLE
-- ================================================================
CREATE TABLE member_interest_distributions (
    distribution_id BIGINT NOT NULL REFERENCES interest_distributions(distribution_id),
    user_id BIGINT NOT NULL REFERENCES saathifund_users(user_id),

    -- Distribution Details
    distribution_amount DECIMAL(12,2) NOT NULL,
    distribution_method distributionmethodenum NOT NULL,

    -- Status
    distribution_status memberinterestdistributionstatusenum NOT NULL DEFAULT 'PENDING',

    distribution_date TIMESTAMP,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY (distribution_id, user_id)
);

-- ================================================================
-- 11. NOTIFICATIONS TABLE
-- ================================================================
CREATE TABLE notifications (
    notification_id BIGINT DEFAULT nextval('seq_notification_id') PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES saathifund_users(user_id),
    committee_id BIGINT REFERENCES committees(committee_id),

    -- Notification Content
    notification_type notificationtypeenum NOT NULL,
    title VARCHAR(200) NOT NULL,
    message TEXT NOT NULL,

    -- Delivery Channels
    send_push BOOLEAN DEFAULT true,
    send_email BOOLEAN DEFAULT true,
    send_sms BOOLEAN DEFAULT false,

    -- Status Tracking
    notification_status notificationstatusenum NOT NULL DEFAULT 'PENDING',

    sent_at TIMESTAMP,
    delivered_at TIMESTAMP,
    read_at TIMESTAMP,

    -- Priority
    priority prioritylevelenum NOT NULL DEFAULT 'MEDIUM',

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ================================================================
-- 12. SYSTEM SETTINGS TABLE
-- ================================================================
CREATE TABLE system_settings (
    setting_key VARCHAR(100) PRIMARY KEY,
    setting_value TEXT NOT NULL,
    setting_type settingstypeenum NOT NULL DEFAULT 'STRING',
    description TEXT,
    is_system_setting BOOLEAN DEFAULT false,
    updated_by BIGINT REFERENCES saathifund_users(user_id),
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ================================================================
-- 13. AUDIT LOGS TABLE
-- ================================================================
CREATE TABLE audit_logs (
    audit_id BIGINT DEFAULT nextval('seq_audit_id') PRIMARY KEY,
    user_id BIGINT REFERENCES saathifund_users(user_id),
    committee_id BIGINT REFERENCES committees(committee_id),

    -- Action Details
    action_type VARCHAR(100) NOT NULL,
    table_name VARCHAR(100),
    record_id BIGINT,

    -- Changes
    old_values JSONB,
    new_values JSONB,

    -- Context
    ip_address INET,
    user_agent TEXT,
    session_id VARCHAR(100),

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ================================================================
-- INDEXES FOR PERFORMANCE
-- ================================================================

-- saathifund_users table indexes
CREATE INDEX idx_users_email ON saathifund_users(email);
CREATE INDEX idx_users_phone ON saathifund_users(phone_number);
CREATE INDEX idx_users_kyc_status ON saathifund_users(kyc_status);

-- Committees table indexes
CREATE INDEX idx_committees_admin ON committees(admin_user_id);
CREATE INDEX idx_committees_status ON committees(committee_status);
CREATE INDEX idx_committees_invite_code ON committees(invite_code);

-- Committee members indexes
CREATE INDEX idx_committee_members_committee ON committee_members(committee_id);
CREATE INDEX idx_committee_members_user ON committee_members(user_id);
CREATE INDEX idx_committee_members_status ON committee_members(membership_status);

-- Monthly contributions indexes
CREATE INDEX idx_contributions_committee_month ON monthly_contributions(committee_id, contribution_year, contribution_month);
CREATE INDEX idx_contributions_user ON monthly_contributions(user_id);
CREATE INDEX idx_contributions_status ON monthly_contributions(payment_status);
CREATE INDEX idx_contributions_due_date ON monthly_contributions(due_date);

-- Loans table indexes
CREATE INDEX idx_loans_committee ON loans(committee_id);
CREATE INDEX idx_loans_borrower ON loans(borrower_user_id);
CREATE INDEX idx_loans_status ON loans(loan_status);
CREATE INDEX idx_loans_application_date ON loans(application_date);

-- EMI schedule indexes
CREATE INDEX idx_emi_loan ON emi_schedule(loan_id);
CREATE INDEX idx_emi_due_date ON emi_schedule(due_date);
CREATE INDEX idx_emi_status ON emi_schedule(payment_status);

-- Transactions indexes
CREATE INDEX idx_transactions_committee ON transactions(committee_id);
CREATE INDEX idx_transactions_user ON transactions(user_id);
CREATE INDEX idx_transactions_type ON transactions(transaction_type);
CREATE INDEX idx_transactions_date ON transactions(created_at);

-- Notifications indexes
CREATE INDEX idx_notifications_user ON notifications(user_id);
CREATE INDEX idx_notifications_type ON notifications(notification_type);
CREATE INDEX idx_notifications_status ON notifications(notification_status);
CREATE INDEX idx_notifications_created ON notifications(created_at);

-- ================================================================
-- TRIGGERS FOR AUTOMATIC UPDATES
-- ================================================================

-- Update timestamp trigger function
CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ language 'plpgsql';

-- Apply update triggers to relevant tables
CREATE TRIGGER update_users_updated_at BEFORE UPDATE ON saathifund_users FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();
CREATE TRIGGER update_committees_updated_at BEFORE UPDATE ON committees FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();
CREATE TRIGGER update_committee_members_updated_at BEFORE UPDATE ON committee_members FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();
CREATE TRIGGER update_monthly_contributions_updated_at BEFORE UPDATE ON monthly_contributions FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();
CREATE TRIGGER update_loans_updated_at BEFORE UPDATE ON loans FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();
CREATE TRIGGER update_emi_schedule_updated_at BEFORE UPDATE ON emi_schedule FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

-- ================================================================
-- SAMPLE DATA INSERTS (Optional - for testing)
-- ================================================================

-- Insert default system settings
INSERT INTO system_settings (setting_key, setting_value, setting_type, description, is_system_setting) VALUES
('DEFAULT_CONTRIBUTION_DUE_DAY', '10', 'INTEGER', 'Default day of month for contribution due date', true),
('DEFAULT_LOAN_APPLICATION_START_DAY', '20', 'INTEGER', 'Default start day for loan applications', true),
('DEFAULT_FINE_PERCENTAGE', '2.00', 'DECIMAL', 'Default fine percentage for late payments', true),
('MAX_MISSED_PAYMENTS_BEFORE_REMOVAL', '2', 'INTEGER', 'Maximum missed payments before member removal', true),
('EMAIL_NOTIFICATION_ENABLED', 'true', 'BOOLEAN', 'Enable email notifications', true),
('SMS_NOTIFICATION_ENABLED', 'true', 'BOOLEAN', 'Enable SMS notifications', true);

-- ================================================================
-- END OF SCHEMA
-- ================================================================