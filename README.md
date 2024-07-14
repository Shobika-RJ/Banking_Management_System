# Banking_Management_System
## Overview

The Banking Management System is a Java-based application that allows users to manage their bank accounts. The application provides functionalities for user registration, login, opening new bank accounts, and performing various banking operations such as crediting, debiting, transferring money, and checking account balances.

## Features

### User Management
- **User Registration**: Users can register by providing their full name, email, and password. Duplicate registrations for the same email are prevented.
- **User Login**: Registered users can log in using their email and password. The system verifies the credentials before granting access.

### Account Management
- **Open New Account**: Users can open a new bank account by providing their full name, initial deposit amount, and a security pin. Each account is assigned a unique account number.
- **Check Account Existence**: The system checks if an account associated with a user's email already exists.

### Banking Operations
- **Credit Money**: Users can credit (add) money to their account after verifying their security pin.
- **Debit Money**: Users can debit (withdraw) money from their account after verifying their security pin. The system checks for sufficient balance before processing the transaction.
- **Transfer Money**: Users can transfer money to another account by providing the receiver's account number, the amount to be transferred, and their security pin. The system ensures sufficient balance and valid account details before completing the transfer.
- **Check Balance**: Users can check their account balance by providing their security pin.

## Database

The project uses Apache Derby as the database for storing user and account information. The following tables are used:

### Users Table
- `full_name`: The full name of the user.
- `email`: The email address of the user (primary key).
- `password`: The password of the user.

### Accounts Table
- `account_number`: The unique account number (primary key).
- `full_name`: The full name of the account holder.
- `email`: The email address associated with the account.
- `balance`: The current balance of the account.
- `security_pin`: The security pin for account verification.
Structure of the database
![image](https://github.com/user-attachments/assets/719001e7-3d92-485b-b0b2-fee09b0c8eb1)
