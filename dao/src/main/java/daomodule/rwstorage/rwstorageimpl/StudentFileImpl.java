package daomodule.rwstorage.rwstorageimpl;

import daomodule.entities.Role;
import daomodule.entities.StudentEntity;
import daomodule.rwstorage.RWStorage;
import daomodule.storage.StudentList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.lang.Integer.parseInt;

public class StudentFileImpl implements RWStorage {
    private Object obj;
    private List<StudentEntity> students;
    private JSONArray jsonArray;

    public StudentFileImpl(){}

    @Override
    public void fillStorage(){
        students=new ArrayList<>();
        try {
             obj = new JSONParser().parse(new FileReader("D:\\Java\\NetProject\\dao\\src\\main\\resources\\json\\students.json"));
             jsonArray = (JSONArray) obj;
             for (Object object:jsonArray) {
                 JSONObject jsonObject = (JSONObject) object;
                 int id = (int) (long) jsonObject.get("id");
                 Role role = Role.valueOf((String) jsonObject.get("role"));
                 String login = (String) jsonObject.get("login");
                 String pass = (String) jsonObject.get("pass");
                 String name = (String) jsonObject.get("name");
                 int studentId = (int) (long) jsonObject.get("studentId");
                 int groupId = (int) (long) jsonObject.get("groupId");
                 int specialityId=(int) (long) jsonObject.get("specialityId");

                 JSONArray marks = (JSONArray) jsonObject.get("writeBook");
                 Iterator<Integer> i = marks.iterator();
                 List<Integer> writeBook = new ArrayList<>();
                 while (i.hasNext()) {
                     writeBook.add(parseInt(String.valueOf(i.next())));
                 }
                 students.add(new StudentEntity(id, role, login, pass, name, studentId, groupId, specialityId,writeBook));
             }
        } catch (IOException | ParseException e){
            e.printStackTrace();
        }
        StudentList.getInstance().setStudents(students);
    }

}
