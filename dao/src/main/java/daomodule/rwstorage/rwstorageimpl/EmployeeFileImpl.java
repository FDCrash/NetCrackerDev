package daomodule.rwstorage.rwstorageimpl;


import daomodule.entities.EmployeeEntity;
import daomodule.entities.Role;
import daomodule.rwstorage.RWStorage;
import daomodule.storage.EmployeeList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeFileImpl implements RWStorage {
    private Object obj;
    private List<EmployeeEntity> employees;
    private JSONArray jsonArray;

    @Override
    public void fillStorage() {
        employees=new ArrayList<>();
        try {
            obj = new JSONParser().parse(new FileReader("dao/src/main/resources/json/employees.json"));
            jsonArray = (JSONArray) obj;
            for (Object object:jsonArray) {
                JSONObject jsonObject = (JSONObject) object;
                int id = (int) (long) jsonObject.get("id");
                Role role = Role.valueOf((String) jsonObject.get("role"));
                String login = (String) jsonObject.get("login");
                String pass = (String) jsonObject.get("pass");
                String name = (String) jsonObject.get("name");

                employees.add(new EmployeeEntity(id, role, login, pass, name));
            }
        } catch (IOException | ParseException e){
            e.printStackTrace();
        }
        EmployeeList.getInstance().set(employees);

    }

    @Override
    public void writeFile() {
        jsonArray = new JSONArray();
        for(EmployeeEntity employeeEntity:EmployeeList.getInstance().get()){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("id" , employeeEntity.getId());
            jsonObject.put("role", "EMPLOYEE");
            jsonObject.put("login", employeeEntity.getLogin());
            jsonObject.put("pass", employeeEntity.getPassword());
            jsonObject.put("name", employeeEntity.getName());
            jsonArray.add(jsonObject);
        }
        try{
            FileWriter file=new FileWriter("dao/src/main/resources/json/employees.json");
            file.write(jsonArray.toJSONString());
            file.flush();
            file.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
