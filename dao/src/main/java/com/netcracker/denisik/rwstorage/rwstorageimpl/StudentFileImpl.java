package com.netcracker.denisik.rwstorage.rwstorageimpl;

import com.netcracker.denisik.entities.Role;
import com.netcracker.denisik.entities.StudentEntity;
import com.netcracker.denisik.entities.UserEntity;
import com.netcracker.denisik.rwstorage.RWStorage;
import com.netcracker.denisik.storage.StudentList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.lang.Integer.parseInt;

public class StudentFileImpl implements RWStorage {
    private Object obj;
    private List<StudentEntity> students;
    private JSONArray jsonArray;

    public StudentFileImpl() {
    }

    @Override
    public void fillStorage() {
        students = new ArrayList<>();
        try {
            obj = new JSONParser().parse(new FileReader(LoadFile.getInstance().getProperties().getProperty("json.students")));
            jsonArray = (JSONArray) obj;
            for (Object object : jsonArray) {
                JSONObject jsonObject = (JSONObject) object;
                int id = (int) (long) jsonObject.get("id");
                Role role = Role.valueOf((String) jsonObject.get("role"));
                String login = (String) jsonObject.get("login");
                String pass = (String) jsonObject.get("pass");
                String name = (String) jsonObject.get("name");
                int studentId = (int) (long) jsonObject.get("studentId");
                int groupId = (int) (long) jsonObject.get("groupId");
                int specialityId = (int) (long) jsonObject.get("specialityId");

                JSONArray marks = (JSONArray) jsonObject.get("writeBook");
                Iterator<Integer> i = marks.iterator();
                List<Integer> writeBook = new ArrayList<>();
                while (i.hasNext()) {
                    writeBook.add(parseInt(String.valueOf(i.next())));
                }
                students.add(new StudentEntity(new UserEntity(id, role, login, pass), name, studentId, groupId, specialityId, writeBook));
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        StudentList.getInstance().set(students);
    }

    @Override
    public void writeFile() {
        jsonArray = new JSONArray();
        for (StudentEntity studentEntity : StudentList.getInstance().get()) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", studentEntity.getId());
            jsonObject.put("role", "STUDENT");
            jsonObject.put("login", studentEntity.getLogin());
            jsonObject.put("pass", studentEntity.getPassword());
            jsonObject.put("name", studentEntity.getName());
            jsonObject.put("studentId", studentEntity.getStudentId());
            jsonObject.put("groupId", studentEntity.getGroupId());
            jsonObject.put("specialityId", studentEntity.getSpecialityEntity().getId());
            JSONArray marks = new JSONArray();
            for (int i : studentEntity.getWriteBook()) {
                marks.add(String.valueOf(i));
            }
            jsonObject.put("writeBook", marks);
            jsonArray.add(jsonObject);
        }
        try {
            FileWriter file = new FileWriter(LoadFile.getInstance().getProperties().getProperty("json.students"));
            file.write(jsonArray.toJSONString());
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
