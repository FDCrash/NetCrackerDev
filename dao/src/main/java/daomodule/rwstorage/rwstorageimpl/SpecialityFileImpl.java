package daomodule.rwstorage.rwstorageimpl;

import daomodule.entities.SpecialityEntity;
import daomodule.rwstorage.RWStorage;
import daomodule.storage.SpecialityList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
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
            obj = new JSONParser().parse(new FileReader("D:\\Java\\NetProject\\dao\\src\\main\\resources\\json\\specialities.json"));
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
        SpecialityList.getInstance().setSpecialities(specialities);
    }
}
