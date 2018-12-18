package com.netcracker.denisik.services.implementations;

import com.netcracker.denisik.converters.UserConverter;
import com.netcracker.denisik.dao.StudentRepository;
import com.netcracker.denisik.dao.UserRepository;
import com.netcracker.denisik.dto.UserDTO;
import com.netcracker.denisik.entities.Role;
import com.netcracker.denisik.entities.Student;
import com.netcracker.denisik.entities.User;
import com.netcracker.denisik.exteption.ResourceAlreadyExistException;
import com.netcracker.denisik.exteption.ResourceNotFoundException;
import com.netcracker.denisik.exteption.ServiceException;
import com.netcracker.denisik.services.CrudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class UserServiceImpl implements CrudService<UserDTO> {
    private UserConverter userConverter;
    private StudentRepository studentRepository;
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserConverter userConverter, StudentRepository studentRepository, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.userConverter = userConverter;
    }


    @Override
    public long add(UserDTO userDTO) {
        if (userRepository.existsByLogin(userDTO.getLogin())) {
            throw new ResourceAlreadyExistException("User exist by login: " + userDTO.getLogin());
        }
        return userRepository.save(userConverter.convert(userDTO)).getId();
    }

    @Override
    public void delete(long id) {
        User user = userRepository.findOne(id);
        if (user == null) {
            throw new ResourceNotFoundException("User not by id: " + id);
        } else if (getAllAdmins().size() < 1) {
            throw new ServiceException("Cant delete last admin" + id);
        }
        userRepository.delete(id);
    }

    @Override
    public List<UserDTO> getAll() {
        List<UserDTO> userDTOS = StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .map(user -> userConverter.convert(user))
                .collect(Collectors.toList());
        log.debug("");
        return userDTOS;
    }

    public List<UserDTO> getAllAdmins() {
        List<UserDTO> userDTOS = StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .filter(user -> user.getRole().equals(Role.ADMIN))
                .map(user -> userConverter.convert(user))
                .collect(Collectors.toList());
        log.debug("");
        return userDTOS;
    }

    public List<UserDTO> getAllEmployees() {
        List<UserDTO> userDTOS = StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .filter(user -> user.getRole().equals(Role.EMPLOYEE))
                .map(employee -> userConverter.convert(employee))
                .collect(Collectors.toList());
        log.debug("");
        return userDTOS;
    }

    @Override
    public UserDTO get(long id) {
        User user = userRepository.findOne(id);
        return userConverter.convert(user);
    }

    public boolean registration(long id, String login, String pass) {
        Student student = studentRepository.getByWriteBookId(id);
        User user = userRepository.findOne(student.getId());
        if (user != null) {
            if (userRepository.existsByLogin(login)) {
                user.setLogin(login);
                user.setPassword(pass);
                userRepository.save(user);
                return true;
            }
            throw new ResourceAlreadyExistException("User exist by login: " + login);
        }
        throw new ResourceNotFoundException("Student not found by writeBookId: " + id);
    }
}
