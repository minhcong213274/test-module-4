package dao;

import model.Staff;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffDAO implements IStaffDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/manager_staff";
    private String jdbcUsername = "root";
    private String jdbcPassword = "123456";
    private static final String INSERT_STAFF_SQL="INSERT INTO staff (name, email, address,phone,salary,department) VALUES (?, ?, ?,?,?,?);";
    private static final String SELECT_STAFF_BY_ID= "select * from staff where id_staff =?";
    private static final String SELECT_ALL_STAFF="select * from staff";
    private static final String DELETE_STAFF_SQL="delete from staff where id_staff = ?;";
    private static final String UPDATE_STAFF_SQL="update staff set name = ?,email= ?, address =?, phone = ?,salary= ?, department =? where id_staff = ?;";
    private static final String SELECT_STAFF_BY_NAME = "SELECT * FROM staff WHERE name LIKE '?%';";
    public StaffDAO(){

    }
    protected Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }return connection;
    }
    @Override
    public void insertStaff(Staff staff) throws SQLException {
        System.out.println(INSERT_STAFF_SQL);
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STAFF_SQL)) {
            preparedStatement.setString(1, staff.getName());
            preparedStatement.setString(2, staff.getEmail());
            preparedStatement.setString(3, staff.getAddress());
            preparedStatement.setString(4, staff.getPhone());
            preparedStatement.setInt(5, staff.getSalary());
            preparedStatement.setString(6, staff.getDepartment());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
           e.printStackTrace();
        }
    }
    @Override

    public Staff selectStaff(int id_staff) {
        Staff staff = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STAFF_BY_ID);) {
            preparedStatement.setInt(1, id_staff);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                int salary = rs.getInt("salary");
                String department = rs.getString("department");
                staff = new Staff(id_staff, name, email, address,phone,salary,department);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staff;
    }
    @Override

    public List<Staff> selectAllStaff() {
        List<Staff> staffs = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STAFF);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id_staff = rs.getInt("id_staff");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                int salary = rs.getInt("salary");
                String department = rs.getString("department");
                staffs.add(new Staff(id_staff, name, email, address,phone,salary,department));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staffs;
    }
    @Override

    public boolean deleteStaff(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_STAFF_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
    @Override

    public boolean updateStaff(Staff staff) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_STAFF_SQL);) {
            statement.setString(1, staff.getName());
            statement.setString(2, staff.getEmail());
            statement.setString(3, staff.getAddress());
            statement.setString(4, staff.getPhone());
            statement.setInt(5, staff.getSalary());
            statement.setString(6, staff.getDepartment());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    @Override
    public Staff selectStaffById() {
        Staff staff = null ;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STAFF_BY_ID);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
                int id_staff = rs.getInt("id_staff");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                int salary = rs.getInt("salary");
                String department = rs.getString("department");
                staff =new Staff(id_staff, name, email, address,phone,salary,department);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staff;
    }

    @Override
    public List<Staff> selectStaffByName() {
        List<Staff> staffs = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STAFF_BY_NAME);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id_staff = rs.getInt("id_staff");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                int salary = rs.getInt("salary");
                String department = rs.getString("department");
                staffs.add(new Staff(id_staff, name, email, address,phone,salary,department));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staffs;
    }

}
