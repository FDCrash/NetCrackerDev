package daomodule.rwstorage.rwstorageimpl;

import daomodule.rwstorage.RWStorage;
import daomodule.storage.AdminList;
import daomodule.storage.EmployeeList;
import daomodule.storage.StudentList;
import daomodule.storage.UserList;

public class UserFileImpl implements RWStorage {
    @Override
    public void fillStorage() {
        UserList.getInstance().setAdmins(AdminList.getInstance().getAdmins());
        UserList.getInstance().setEmployees(EmployeeList.getInstance().getEmployees());
        UserList.getInstance().setStudents(StudentList.getInstance().getStudents());
    }
}
