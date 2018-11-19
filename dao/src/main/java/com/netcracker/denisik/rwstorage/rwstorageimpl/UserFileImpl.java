package com.netcracker.denisik.rwstorage.rwstorageimpl;

import com.netcracker.denisik.rwstorage.RWStorage;
import com.netcracker.denisik.storage.AdminList;
import com.netcracker.denisik.storage.EmployeeList;
import com.netcracker.denisik.storage.StudentList;
import com.netcracker.denisik.storage.UserList;

public class UserFileImpl implements RWStorage {
    @Override
    public void fillStorage() {
        UserList.getInstance().setAdmins(AdminList.getInstance().get());
        UserList.getInstance().setEmployees(EmployeeList.getInstance().get());
        UserList.getInstance().setStudents(StudentList.getInstance().get());
    }
}
