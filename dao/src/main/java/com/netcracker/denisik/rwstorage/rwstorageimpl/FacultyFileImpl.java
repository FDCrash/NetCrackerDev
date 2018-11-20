package com.netcracker.denisik.rwstorage.rwstorageimpl;

import com.netcracker.denisik.entities.FacultyEntity;
import com.netcracker.denisik.rwstorage.RWStorage;
import com.netcracker.denisik.storage.FacultyList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FacultyFileImpl implements RWStorage {
    private Object obj;
    private List<FacultyEntity> faculties;
    private JSONArray jsonArray;

    @Override
    public void fillStorage() {
        faculties=new ArrayList<>();
        try {
            obj = new JSONParser().parse(new FileReader(LoadFile.getInstance().getProperties().getProperty("json.faculties")));
            jsonArray = (JSONArray) obj;
            for (Object object:jsonArray) {
                JSONObject jsonObject = (JSONObject) object;
                int id = (int) (long) jsonObject.get("id");
                String name = (String) jsonObject.get("name");
                faculties.add(new FacultyEntity(id,name));
            }
        } catch (IOException | ParseException e){
            e.printStackTrace();
        }

        FacultyList.getInstance().set(faculties);
    }

    @Override
    public void writeFile() {
        jsonArray = new JSONArray();
        for(FacultyEntity facultyEntity:FacultyList.getInstance().get()){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("id" , facultyEntity.getId());
            jsonObject.put("name", facultyEntity.getName());
            jsonArray.add(jsonObject);
        }
        try{
            FileWriter file=new FileWriter(LoadFile.getInstance().getProperties().getProperty("json.faculties"));
            file.write(jsonArray.toJSONString());
            file.flush();
            file.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
