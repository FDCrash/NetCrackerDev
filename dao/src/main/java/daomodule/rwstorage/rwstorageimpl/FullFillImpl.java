package daomodule.rwstorage.rwstorageimpl;

import daomodule.entities.FacultyEntity;
import daomodule.entities.SpecialityEntity;
import daomodule.entities.StudentEntity;
import daomodule.rwstorage.RWStorage;
import daomodule.storage.FacultyList;
import daomodule.storage.SpecialityList;
import daomodule.storage.StudentList;

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
