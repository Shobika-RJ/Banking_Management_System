package bankingmanagement;

import java.sql.*;
import java.util.*;

public class Accounts {

    private Connection connection;
    private Scanner scanner;

    public Accounts(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }

    public long open_account(String email) {
        if (!account_exist(email)) {
            String open_account_query = "INSERT INTO Accounts(account_number, full_name, email, balance, security_pin) VALUES(?, ?, ?, ?, ?)";
            scanner.nextLine();
            System.out.print("Enter Full Name: ");
            String full_name = scanner.nextLine();
            System.out.print("Enter Initial Amount: ");
            double balance = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Enter Security Pin: ");
            String security_pin = scanner.nextLine();

            try {
                long account_number;
                boolean success = false;
                while (!success) {
                    account_number = generateAccountNumber();
                    try {
                        PreparedStatement preparedStatement = connection.prepareStatement(open_account_query);
                        preparedStatement.setLong(1, account_number);
                        preparedStatement.setString(2, full_name);
                        preparedStatement.setString(3, email);
                        preparedStatement.setDouble(4, balance);
                        preparedStatement.setString(5, security_pin);
                        int rowsAffected = preparedStatement.executeUpdate();
                        if (rowsAffected > 0) {
                            return account_number;
                        }
                    } catch (SQLIntegrityConstraintViolationException e) {
                        // Account number collision, generate a new number
                        continue;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        throw new RuntimeException("Account Already Exists");
    }

    public long getAccount_number(String email) {
        String query = "SELECT account_number FROM Accounts WHERE email = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong("account_number");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Account Number Doesn't Exist!");
    }

    private long generateAccountNumber() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT MAX(account_number) AS max_account_number FROM Accounts");
            if (resultSet.next()) {
                long last_account_number = resultSet.getLong("max_account_number");
                return last_account_number + 1;
            } else {
                return 10000100;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 10000100;
    }

    public boolean account_exist(String email) {
        String query = "SELECT account_number FROM Accounts WHERE email = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
