package com.netcracker.denisik.rwstorage.rwstorageimpl;

import com.netcracker.denisik.entities.FacultyEntity;
import com.netcracker.denisik.entities.SpecialityEntity;
import com.netcracker.denisik.entities.StudentEntity;
import com.netcracker.denisik.rwstorage.RWStorage;
import com.netcracker.denisik.storage.FacultyList;
import com.netcracker.denisik.storage.SpecialityList;
import com.netcracker.denisik.storage.StudentList;

public class FullFillImpl implements RWStorage {
    private EmployeeFileImpl employeeFile;
    private AdminFileImpl adminFile;
    private StudentFileImpl studentFile;
    private SpecialityFileImpl specialityFile;
    private FacultyFileImpl facultyFile;

    public FullFillImpl(){
        employeeFile =new EmployeeFileImpl();
        adminFile = new AdminFileImpl();
        studentFile = new StudentFileImpl();
        specialityFile = new SpecialityFileImpl();
        facultyFile = new FacultyFileImpl();
    }
    @Override
    public void fillStorage() {
        employeeFile.fillStorage();
        adminFile.fillStorage();
        studentFile.fillStorage();
        specialityFile.fillStorage();
        facultyFile.fillStorage();
        connectStudentSpeciality();
        connectSpecialityFaculty();
        new UserFileImpl().fillStorage();
    }

    @Override
    public void writeFile() {
        employeeFile.fillStorage();
        adminFile.fillStorage();
        studentFile.fillStorage();
        specialityFile.fillStorage();
        facultyFile.fillStorage();
    }

    private void connectStudentSpeciality() {
        for (StudentEntity studentEntity : StudentList.getInstance().get()) {
            for (SpecialityEntity specialityEntity : SpecialityList.getInstance().get()) {
                if (studentEntity.getSpecialityEntity().getId() == specialityEntity.getId()) {
                    studentEntity.setSpecialityEntity(specialityEntity);
                }
            }
        }
    }

    private void connectSpecialityFaculty() {
        for (SpecialityEntity specialityEntity : SpecialityList.getInstance().get()) {
            for (FacultyEntity facultyEntity : FacultyList.getInstance().get()) {
                if (specialityEntity.getFaculty().getId() == facultyEntity.getId()) {
                    specialityEntity.setFaculty(facultyEntity);
                    facultyEntity.setSpeciality(specialityEntity);
                }
            }
        }
    }

}
