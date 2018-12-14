package com.netcracker.denisik.services.servicesimpl;

import com.netcracker.denisik.converters.UserConverter;
import com.netcracker.denisik.dao.StudentRepository;
import com.netcracker.denisik.dao.UserRepository;
import com.netcracker.denisik.dto.UserDTO;
import com.netcracker.denisik.entities.Role;
import com.netcracker.denisik.entities.Student;
import com.netcracker.denisik.entities.User;
import com.netcracker.denisik.services.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserServiceImpl implements CRUDService<UserDTO> {
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void add(UserDTO userDTO) {
        userRepository.save(userConverter.convert(userDTO));
    }

    @Override
    public void delete(long id) {
        userRepository.delete(id);
    }

    @Override
    public void update(UserDTO userDTO) {
        userRepository.save(userConverter.convert(userDTO));
    }

    @Override
    public List<UserDTO> getAll() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .map(user -> userConverter.convert(user))
                .collect(Collectors.toList());
    }

    public List<UserDTO> getAllAdmins() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .filter(user -> user.getRole().equals(Role.ADMIN))
                .map(user -> userConverter.convert(user)).collect(Collectors.toList());
    }

    public List<UserDTO> getAllEmployees() {
        return StreamSupport.stream(userRepository.findAll().spliterator(),false)
                .filter(user -> user.getRole().equals(Role.EMPLOYEE))
                .map(employee -> userConverter.convert(employee))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO get(long id) {
        return userConverter.convert(userRepository.findOne(id));
    }

    public boolean registration(int id, String login, String pass) {
        Student student =studentRepository.getByStudentId(id);
        User user =userRepository.findOne(student.getId());
        if (isNull(student)) {
            if (checkLogin(login)) {
                user.setLogin(login);
                user.setPassword(pass);
                userRepository.save(user);
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean checkLogin(String login) {
        return isNull(userRepository.getByLogin(login));
    }

    public Enum authentication(String login, String pass) {
        return userRepository.getRoleByLoginAndPassword(login, pass).getRole();
    }

    public boolean isNull(Object obj){
        return obj==null;
    }
}
