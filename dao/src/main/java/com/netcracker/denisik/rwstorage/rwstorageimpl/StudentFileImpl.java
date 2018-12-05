package com.netcracker.denisik.rwstorage.rwstorageimpl;

import com.netcracker.denisik.entities.*;
import com.netcracker.denisik.rwstorage.RWStorage;
import com.netcracker.denisik.storage.StudentList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

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
                JSONArray semester = (JSONArray) jsonObject.get("writeBook");
                List<Semester> semesters = new ArrayList<>();
                for(Object object1:semester){
                    JSONObject jsonObj= (JSONObject) object1;
                    JSONArray subject= (JSONArray) jsonObj.get("subjects");
                    Iterator<String> iteratorSubj= subject.iterator();
                    List<String> subjects = new ArrayList<>();
                    while(iteratorSubj.hasNext()){
                        subjects.add(iteratorSubj.next());
                    }
                    JSONArray mark=(JSONArray) jsonObj.get("marks");
                    Iterator<Long> iteratorMark= mark.iterator();
                    List<Integer> marks= new ArrayList<>();
                    while(iteratorMark.hasNext()){
                        marks.add(iteratorMark.next().intValue());
                    }
                    semesters.add(new Semester((int) (long)jsonObj.get("semester"),subjects,marks));
                }
                students.add(new StudentEntity(new UserEntity(id,role,login,pass),
                        name,studentId,groupId,specialityId, new WriteBook(semester)));
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
            JSONArray writeBook = new JSONArray();
            int i=0;
            for (Semester semesters:studentEntity.getWriteBook().getSemester()) {
                JSONObject sem=new JSONObject();
                sem.put("semester", semesters.getSem());
                JSONArray subject= new JSONArray();
                for(String s: studentEntity.getWriteBook().getSemester().get(i).getSubjects()){
                    subject.add(s);
                }
                sem.put("subjects", subject);
                JSONArray mark= new JSONArray();
                for(Integer marks: studentEntity.getWriteBook().getSemester().get(i).getMarks()){
                    mark.add(marks);
                }
                sem.put("marks", mark);
                writeBook.add(sem);
            }
            jsonObject.put("writeBook", writeBook);
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
