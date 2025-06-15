package com.anterka.saathifund.constants;

public class SaathiFundTables {

    private SaathiFundTables(){}

    public static class SaathiFundUsers {
        public static final String TABLE_NAME = "saathifund_users";
        public static final String SEQUENCE_NAME = "seq_saathifund_user_id";
        /**
         * [saathi_fund_users] table's column names below
         * */
        public static final String ID = "user_id";
        public static final String FIRST_NAME = "first_name";
        public static final String LAST_NAME = "last_name";
        public static final String USER_NAME = "user_name";
        public static final String EMAIL = "email";
        public static final String PASSWORD = "password_hash";
        public static final String PHONE_NUMBER = "phone_number";
        public static final String DATE_OF_BIRTH = "date_of_birth";
        public static final String ADDRESS = "address";
        public static final String CITY = "city";
        public static final String STATE = "state";
        public static final String PIN_CODE = "pin_code";

       // KYC related fields
        public static final String AADHAR_NUMBER = "aadhaar_number";
        public static final String PAN_NUMBER = "pan_number";
        public static final String KYC_STATUS = "kyc_status";

        // Bank details
        public static final String BANK_NAME = "bank_name";
        public static final String BANK_ACCOUNT_NUMBER = "bank_account_number";
        public static final String BANK_IFSC_CODE = "bank_ifsc_code";
        public static final String ACCOUNT_HOLDER_NAME = "account_holder_name";

        public static final String IS_ACTIVE = "is_active";
        public static final String EMAIL_VERIFIED = "email_verified";
        public static final String PHONE_VERIFIED = "phone_verified";
        public static final String CREATED_AT = "created_at";
        public static final String UPDATED_AT = "updated_at";
        public static final String LAST_LOGIN_AT = "last_login_at";

        private SaathiFundUsers(){}
    }

    public static class Committees {
        public static final String TABLE_NAME = "committees";
        public static final String SEQUENCE_NAME = "seq_committee_id";
        /**
         * [committees] table's column names below
         * */
        public static final String ID = "committee_id";
        public static final String ADMIN_USER_ID = "admin_user_id";
        public static final String COMMITTEE_NAME = "committee_name";
        public static final String DESCRIPTION = "description";

        // Financial Configuration
        public static final String MONTHLY_CONTRIBUTION_AMOUNT = "monthly_contribution_amount";
        public static final String MAX_MEMBERS = "max_members";
        public static final String CURRENT_MEMBER_COUNT = "current_member_count";

        // Loan Configuration
        public static final String MAX_LOAN_AMOUNT = "max_loan_amount";
        public static final String MIN_INTEREST_RATE = "min_interest_rate";
        public static final String MAX_INTEREST_RATE = "max_interest_rate";
        public static final String MAX_LOAN_TENURE_MONTHS = "max_loan_tenure_months";

        // Settings
        public static final String LATE_PAYMENT_FINE_AMOUNT = "late_payment_fine_amount";
        public static final String LATE_PAYMENT_FINE_PERCENTAGE = "late_payment_fine_percentage";
        public static final String INVITE_CODE = "invite_code";

        // Status and Control
        public static final String COMMITTEE_STATUS = "committee_status";
        public static final String LOAN_APPLICATIONS_PAUSED = "loan_applications_paused";

        // Financial Year
        public static final String FINANCIAL_YEAR_START_MONTH = "financial_year_start_month";

        public static final String CREATED_AT = "created_at";
        public static final String UPDATED_AT = "updated_at";

        private Committees(){}
    }

    public static class CommitteeMembers {
        public static final String TABLE_NAME = "committee_members";
        public static final String SEQUENCE_NAME = "seq_membership_id";
        /**
         * [committee_members] table's column names below
         * */
        public static final String ID = "membership_id";
        public static final String COMMITTEE_ID = "committee_id";
        public static final String USER_ID = "user_id";

        // Membership Details
        public static final String MEMBERSHIP_STATUS = "membership_status";
        public static final String JOIN_REQUEST_DATE = "join_request_date";
        public static final String APPROVED_DATE = "approved_date";
        public static final String APPROVED_BY = "approved_by";

        // Member Role
        public static final String MEMBER_ROLE = "member_role";

        // Financial Tracking
        public static final String TOTAL_CONTRIBUTIONS_PAID = "total_contributions_paid";
        public static final String TOTAL_INTEREST_EARNED = "total_interest_earned";
        public static final String MISSED_PAYMENTS_COUNT = "missed_payments_count";
        public static final String CURRENT_FINE_AMOUNT = "current_fine_amount";

        public static final String CREATED_AT = "created_at";
        public static final String UPDATED_AT = "updated_at";

        private CommitteeMembers(){}
    }

    public static class MonthlyContributions {
        public static final String TABLE_NAME = "monthly_contributions";
        public static final String SEQUENCE_NAME = "seq_contribution_id";
        /**
         * [monthly_contributions] table's column names below
         * */
        public static final String ID = "contribution_id";
        public static final String COMMITTEE_ID = "committee_id";
        public static final String USER_ID = "user_id";

        // Contribution Period
        public static final String CONTRIBUTION_YEAR = "contribution_year";
        public static final String CONTRIBUTION_MONTH = "contribution_month";

        // Financial Details
        public static final String EXPECTED_AMOUNT = "expected_amount";
        public static final String PAID_AMOUNT = "paid_amount";
        public static final String FINE_AMOUNT = "fine_amount";

        // Payment Details
        public static final String PAYMENT_STATUS = "payment_status";
        public static final String PAYMENT_METHOD = "payment_method";
        public static final String PAYMENT_DATE = "payment_date";
        public static final String PAYMENT_REFERENCE = "payment_reference";

        // Deadlines
        public static final String DUE_DATE = "due_date";
        public static final String GRACE_PERIOD_END_DATE = "grace_period_end_date";

        public static final String CREATED_AT = "created_at";
        public static final String UPDATED_AT = "updated_at";

        private MonthlyContributions(){}
    }

    public static class Loans {
        public static final String TABLE_NAME = "loans";
        public static final String SEQUENCE_NAME = "seq_loan_id";
        /**
         * [loans] table's column names below
         * */
        public static final String ID = "loan_id";
        public static final String COMMITTEE_ID = "committee_id";
        public static final String BORROWER_USER_ID = "borrower_user_id";

        // Loan Application Details
        public static final String APPLICATION_DATE = "application_date";
        public static final String LOAN_PURPOSE = "loan_purpose";
        public static final String REQUESTED_AMOUNT = "requested_amount";
        public static final String PRIORITY_LEVEL = "priority_level";
        public static final String SUPPORTING_DOCUMENTS = "supporting_documents";

        // Approval Details
        public static final String APPROVED_AMOUNT = "approved_amount";
        public static final String INTEREST_RATE = "interest_rate";
        public static final String TENURE_MONTHS = "tenure_months";
        public static final String MINIMUM_MONTHLY_PAYMENT = "minimum_monthly_payment";

        // Loan Status
        public static final String LOAN_STATUS = "loan_status";

        // Administrative Actions
        public static final String REVIEWED_BY = "reviewed_by";
        public static final String REVIEWED_DATE = "reviewed_date";
        public static final String APPROVAL_COMMENTS = "approval_comments";
        public static final String REJECTION_REASON = "rejection_reason";

        // Disbursement Details
        public static final String DISBURSEMENT_METHOD = "disbursement_method";
        public static final String DISBURSEMENT_DATE = "disbursement_date";
        public static final String DISBURSEMENT_REFERENCE = "disbursement_reference";

        // Repayment Tracking
        public static final String TOTAL_REPAID_AMOUNT = "total_repaid_amount";
        public static final String OUTSTANDING_PRINCIPAL = "outstanding_principal";
        public static final String OUTSTANDING_INTEREST = "outstanding_interest";
        public static final String TOTAL_INTEREST_PAID = "total_interest_paid";

        // Loan Completion
        public static final String LOAN_COMPLETION_DATE = "loan_completion_date";
        public static final String EARLY_CLOSURE = "early_closure";

        public static final String CREATED_AT = "created_at";
        public static final String UPDATED_AT = "updated_at";

        private Loans(){}
    }

    public static class LoanGuarantors {
        public static final String TABLE_NAME = "loan_guarantors";
        public static final String SEQUENCE_NAME = "seq_guarantor_id";
        /**
         * [loan_guarantors] table's column names below
         * */
        public static final String ID = "guarantor_id";
        public static final String LOAN_ID = "loan_id";
        public static final String GUARANTOR_USER_ID = "guarantor_user_id";

        // Guarantor Details
        public static final String GUARANTEE_AMOUNT = "guarantee_amount";
        public static final String GUARANTOR_STATUS = "guarantor_status";
        public static final String GUARANTEE_START_DATE = "guarantee_start_date";
        public static final String GUARANTEE_END_DATE = "guarantee_end_date";

        public static final String CREATED_AT = "created_at";

        private LoanGuarantors(){}
    }

    public static class EmiSchedule {
        public static final String TABLE_NAME = "emi_schedule";
        public static final String SEQUENCE_NAME = "seq_emi_id";
        /**
         * [emi_schedule] table's column names below
         * */
        public static final String ID = "emi_id";
        public static final String LOAN_ID = "loan_id";

        // EMI Details
        public static final String EMI_NUMBER = "emi_number";
        public static final String DUE_DATE = "due_date";
        public static final String PRINCIPAL_AMOUNT = "principal_amount";
        public static final String INTEREST_AMOUNT = "interest_amount";
        public static final String TOTAL_EMI_AMOUNT = "total_emi_amount";

        // Payment Status
        public static final String PAYMENT_STATUS = "payment_status";
        public static final String PAID_AMOUNT = "paid_amount";
        public static final String PAYMENT_DATE = "payment_date";
        public static final String PAYMENT_METHOD = "payment_method";
        public static final String PAYMENT_REFERENCE = "payment_reference";

        // Late Payment
        public static final String LATE_PAYMENT_FINE = "late_payment_fine";
        public static final String DAYS_OVERDUE = "days_overdue";

        public static final String CREATED_AT = "created_at";
        public static final String UPDATED_AT = "updated_at";

        private EmiSchedule(){}
    }

    public static class Transactions {
        public static final String TABLE_NAME = "transactions";
        public static final String SEQUENCE_NAME = "seq_transaction_id";
        /**
         * [transactions] table's column names below
         * */
        public static final String ID = "transaction_id";
        public static final String COMMITTEE_ID = "committee_id";
        public static final String USER_ID = "user_id";

        // Transaction Details
        public static final String TRANSACTION_TYPE = "transaction_type";
        public static final String AMOUNT = "amount";
        public static final String TRANSACTION_STATUS = "transaction_status";

        // Reference Information
        public static final String REFERENCE_ID = "reference_id";
        public static final String REFERENCE_TYPE = "reference_type";

        // Payment Details
        public static final String PAYMENT_METHOD = "payment_method";
        public static final String PAYMENT_GATEWAY = "payment_gateway";
        public static final String GATEWAY_TRANSACTION_ID = "gateway_transaction_id";
        public static final String GATEWAY_RESPONSE = "gateway_response";

        // Metadata
        public static final String DESCRIPTION = "description";
        public static final String NOTES = "notes";
        public static final String PROCESSED_BY = "processed_by";

        public static final String CREATED_AT = "created_at";

        private Transactions(){}
    }

    public static class InterestDistributions {
        public static final String TABLE_NAME = "interest_distributions";
        public static final String SEQUENCE_NAME = "seq_distribution_id";
        /**
         * [interest_distributions] table's column names below
         * */
        public static final String ID = "distribution_id";
        public static final String COMMITTEE_ID = "committee_id";

        // Distribution Period
        public static final String FINANCIAL_YEAR = "financial_year";
        public static final String DISTRIBUTION_DATE = "distribution_date";

        // Financial Summary
        public static final String TOTAL_INTEREST_COLLECTED = "total_interest_collected";
        public static final String TOTAL_MEMBERS_ELIGIBLE = "total_members_eligible";
        public static final String PER_MEMBER_DISTRIBUTION = "per_member_distribution";

        // Status
        public static final String DISTRIBUTION_STATUS = "distribution_status";

        public static final String CREATED_AT = "created_at";
        public static final String PROCESSED_AT = "processed_at";

        private InterestDistributions(){}
    }

    public static class MemberInterestDistributions {
        public static final String TABLE_NAME = "member_interest_distributions";
        /**
         * [member_interest_distributions] table's column names below
         * */
        public static final String DISTRIBUTION_ID = "distribution_id";
        public static final String USER_ID = "user_id";

        // Distribution Details
        public static final String DISTRIBUTION_AMOUNT = "distribution_amount";
        public static final String DISTRIBUTION_METHOD = "distribution_method";

        // Status
        public static final String DISTRIBUTION_STATUS = "distribution_status";
        public static final String DISTRIBUTION_DATE = "distribution_date";

        public static final String CREATED_AT = "created_at";

        private MemberInterestDistributions(){}
    }

    public static class Notifications {
        public static final String TABLE_NAME = "notifications";
        public static final String SEQUENCE_NAME = "seq_notification_id";
        /**
         * [notifications] table's column names below
         * */
        public static final String ID = "notification_id";
        public static final String USER_ID = "user_id";
        public static final String COMMITTEE_ID = "committee_id";

        // Notification Content
        public static final String NOTIFICATION_TYPE = "notification_type";
        public static final String TITLE = "title";
        public static final String MESSAGE = "message";

        // Delivery Channels
        public static final String SEND_PUSH = "send_push";
        public static final String SEND_EMAIL = "send_email";
        public static final String SEND_SMS = "send_sms";

        // Status Tracking
        public static final String NOTIFICATION_STATUS = "notification_status";
        public static final String SENT_AT = "sent_at";
        public static final String DELIVERED_AT = "delivered_at";
        public static final String READ_AT = "read_at";

        // Priority
        public static final String PRIORITY = "priority";

        public static final String CREATED_AT = "created_at";

        private Notifications(){}
    }

    public static class SystemSettings {
        public static final String TABLE_NAME = "system_settings";
        /**
         * [system_settings] table's column names below
         * */
        public static final String SETTING_KEY = "setting_key";
        public static final String SETTING_VALUE = "setting_value";
        public static final String SETTING_TYPE = "setting_type";
        public static final String DESCRIPTION = "description";
        public static final String IS_SYSTEM_SETTING = "is_system_setting";
        public static final String UPDATED_BY = "updated_by";
        public static final String UPDATED_AT = "updated_at";

        private SystemSettings(){}
    }

    public static class AuditLogs {
        public static final String TABLE_NAME = "audit_logs";
        public static final String SEQUENCE_NAME = "seq_audit_id";
        /**
         * [audit_logs] table's column names below
         * */
        public static final String ID = "audit_id";
        public static final String USER_ID = "user_id";
        public static final String COMMITTEE_ID = "committee_id";

        // Action Details
        public static final String ACTION_TYPE = "action_type";
        public static final String TABLE_NAME_FIELD = "table_name";
        public static final String RECORD_ID = "record_id";

        // Changes
        public static final String OLD_VALUES = "old_values";
        public static final String NEW_VALUES = "new_values";

        // Context
        public static final String IP_ADDRESS = "ip_address";
        public static final String USER_AGENT = "user_agent";
        public static final String SESSION_ID = "session_id";

        public static final String CREATED_AT = "created_at";

        private AuditLogs(){}
    }
}
