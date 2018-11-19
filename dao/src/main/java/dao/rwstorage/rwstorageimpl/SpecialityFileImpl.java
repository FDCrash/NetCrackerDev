package dao.rwstorage.rwstorageimpl;

import dao.entities.SpecialityEntity;
import dao.rwstorage.RWStorage;
import dao.storage.SpecialityList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SpecialityFileImpl implements RWStorage {
    private Object obj;
    private List<SpecialityEntity> specialities;
    private JSONArray jsonArray;

    @Override
    public void fillStorage() {
        specialities=new ArrayList<>();
        try {
            obj = new JSONParser().parse(new FileReader("dao/src/main/resources/json/specialities.json"));
            jsonArray = (JSONArray) obj;
            for (Object object:jsonArray) {
                JSONObject jsonObject = (JSONObject) object;
                int id = (int) (long) jsonObject.get("id");
                String name = (String) jsonObject.get("name");
                int facultyId=(int)(long) jsonObject.get("facultyId");
                specialities.add(new SpecialityEntity(id,name,facultyId));
            }
        } catch (IOException | ParseException e){
            e.printStackTrace();
        }
        SpecialityList.getInstance().set(specialities);
    }

    @Override
    public void writeFile() {
        jsonArray = new JSONArray();
        for(SpecialityEntity specialityEntity:SpecialityList.getInstance().get()){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("id" , specialityEntity.getId());
            jsonObject.put("name", specialityEntity.getName());
            jsonObject.put("facultyId", specialityEntity.getFaculty().getId());
            jsonArray.add(jsonObject);
        }
        try{
            FileWriter file=new FileWriter("dao/src/main/resources/json/specialities.json");
            file.write(jsonArray.toJSONString());
            file.flush();
            file.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
