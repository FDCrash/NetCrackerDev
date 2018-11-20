package com.netcracker.denisik.rwstorage.rwstorageimpl;

import com.netcracker.denisik.entities.UserEntity;
import com.netcracker.denisik.rwstorage.RWStorage;
import com.netcracker.denisik.storage.AdminList;
import com.netcracker.denisik.entities.AdminEntity;
import com.netcracker.denisik.entities.Role;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdminFileImpl implements RWStorage {
    private Object obj;
    private List<AdminEntity> admins;
    private JSONArray jsonArray;

    @Override
    public void fillStorage() {
        admins=new ArrayList<>();
        try {
            obj = new JSONParser().parse(new FileReader(LoadFile.getInstance().getProperties().getProperty("json.admins")));
            jsonArray = (JSONArray) obj;
            for (Object object:jsonArray) {
                JSONObject jsonObject = (JSONObject) object;
                int id = (int) (long) jsonObject.get("id");
                Role role = Role.valueOf((String) jsonObject.get("role"));
                String login = (String) jsonObject.get("login");
                String pass = (String) jsonObject.get("pass");
                admins.add(new AdminEntity(new UserEntity(id, role, login, pass),false));
            }
        } catch (IOException | ParseException e){
            e.printStackTrace();
        }
        AdminList.getInstance().set(admins);
    }

    @Override
    public void writeFile() {
        jsonArray = new JSONArray();
        for(AdminEntity adminEntity:AdminList.getInstance().get()){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("id" , adminEntity.getId());
            jsonObject.put("role", "ADMIN");
            jsonObject.put("login", adminEntity.getLogin());
            jsonObject.put("pass", adminEntity.getPassword());
            jsonArray.add(jsonObject);
        }
        try{
            FileWriter file=new FileWriter(LoadFile.getInstance().getProperties().getProperty("json.admins"));
            file.write(jsonArray.toJSONString());
            file.flush();
            file.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
