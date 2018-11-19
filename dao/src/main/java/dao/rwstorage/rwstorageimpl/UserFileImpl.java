package dao.rwstorage.rwstorageimpl;

import dao.rwstorage.RWStorage;
import dao.storage.AdminList;
import dao.storage.EmployeeList;
import dao.storage.StudentList;
import dao.storage.UserList;

public class UserFileImpl implements RWStorage {
    @Override
    public void fillStorage() {
        UserList.getInstance().setAdmins(AdminList.getInstance().get());
        UserList.getInstance().setEmployees(EmployeeList.getInstance().get());
        UserList.getInstance().setStudents(StudentList.getInstance().get());
    }
}
