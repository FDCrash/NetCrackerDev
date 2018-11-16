package daomodule.rwstorage.rwstorageimpl;

import daomodule.entities.FacultyEntity;
import daomodule.rwstorage.RWStorage;
import daomodule.storage.FacultyList;
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
            obj = new JSONParser().parse(new FileReader("dao/src/main/resources/json/faculties.json"));
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
            FileWriter file=new FileWriter("dao/src/main/resources/json/faculties.json");
            file.write(jsonArray.toJSONString());
            file.flush();
            file.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
