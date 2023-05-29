package dao;

import model.Staff;

import java.sql.SQLException;
import java.util.List;

public interface IStaffDAO {
     void insertStaff(Staff staff) throws SQLException;

     Staff selectStaff(int id);

     List<Staff> selectAllStaff();

     boolean deleteStaff(int id) throws SQLException;

     boolean updateStaff(Staff staff) throws SQLException;
     Staff selectStaffById();
     List<Staff> selectStaffByName();
}
