package com.netcracker.denisik.rwstorage.rwstorageimpl;

import com.netcracker.denisik.entities.SpecialityEntity;
import com.netcracker.denisik.rwstorage.RWStorage;
import com.netcracker.denisik.storage.FacultyList;
import com.netcracker.denisik.storage.SpecialityList;
import com.netcracker.denisik.storage.StudentList;
import com.netcracker.denisik.entities.FacultyEntity;
import com.netcracker.denisik.entities.StudentEntity;

public class FullFillImpl implements RWStorage {
    @Override
    public void fillStorage() {
        new EmployeeFileImpl().fillStorage();
        new AdminFileImpl().fillStorage();
        new StudentFileImpl().fillStorage();
        new SpecialityFileImpl().fillStorage();
        new FacultyFileImpl().fillStorage();
        connectStudentSpeciality();
        connectSpecialityFaculty();
        new UserFileImpl().fillStorage();
    }

    @Override
    public void writeFile() {
        new EmployeeFileImpl().writeFile();
        new AdminFileImpl().writeFile();
        new StudentFileImpl().writeFile();
        new SpecialityFileImpl().writeFile();
        new FacultyFileImpl().writeFile();
    }

    private void connectStudentSpeciality(){
        for (StudentEntity studentEntity: StudentList.getInstance().get()) {
            for (SpecialityEntity specialityEntity : SpecialityList.getInstance().get()) {
                if (studentEntity.getSpecialityEntity().getId() ==specialityEntity.getId()){
                    studentEntity.setSpecialityEntity(specialityEntity);
                }
            }
        }
    }
    private void connectSpecialityFaculty(){
        for (SpecialityEntity specialityEntity : SpecialityList.getInstance().get()) {
            for (FacultyEntity facultyEntity: FacultyList.getInstance().get()) {
                if (specialityEntity.getFaculty().getId() ==facultyEntity.getId()){
                    specialityEntity.setFaculty(facultyEntity);
                    facultyEntity.setSpeciality(specialityEntity);
                }
            }
        }
    }

}
