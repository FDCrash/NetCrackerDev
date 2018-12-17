package com.netcracker.denisik.controllers;

import com.netcracker.denisik.dto.RoleDTO;
import com.netcracker.denisik.dto.StudentDTO;
import com.netcracker.denisik.dto.StudentFormDTO;
import com.netcracker.denisik.dto.UserDTO;
import com.netcracker.denisik.services.servicesimpl.StudentServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@RequestMapping("/students")
public class StudentController {
    private final PasswordEncoder passwordEncoder;
    private final StudentServiceImpl studentService;

    @Autowired
    public StudentController(PasswordEncoder passwordEncoder, StudentServiceImpl studentService) {
        this.passwordEncoder = passwordEncoder;
        this.studentService = studentService;
    }

    @ApiOperation(value = "Gets all students", nickname = "StudentController.getAllStudents")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Students")})
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        List<StudentDTO> student = studentService.getAll();
        if (CollectionUtils.isEmpty(student)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @ApiOperation(value = "Get specific student", nickname = "StudentController.getStudent")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Student")})
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentDTO> getSubject(@PathVariable("id") long id) {
        StudentDTO student = studentService.get(id);
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @ApiOperation(value = "Add student", nickname = "StudentController.addStudent")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Student is adding")})
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createSubject(@RequestBody StudentFormDTO studentFormDTO) {
        try {
            StudentDTO studentDTO= StudentDTO.builderStudent()
                    .userDTO(
                            UserDTO.builder()
                                    .id(studentFormDTO.getId())
                                    .login(studentFormDTO.getLogin())
                                    .name(studentFormDTO.getName())
                                    .password(passwordEncoder.encode(studentFormDTO.getPassword()))
                                    .roleDTO(RoleDTO.STUDENT)
                                    .build())
                    .groupId(studentFormDTO.getGroupId())
                    .specialityId(studentFormDTO.getSpecialityId())
                    .writeBook(studentFormDTO.getWriteBook())
                    .build();
            long id = studentService.add(studentDTO);
            return new ResponseEntity<>(id, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error in creation student", HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "Delete student", nickname = "StudentController.deleteStudent")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Student is deleted")})
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteSubject(@PathVariable("id") Long id) {
        try {
            studentService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "Gets all students by speciality", nickname = "StudentController.getAllStudentsBySpeciality")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "StudentsBySpeciality")})
    @GetMapping(value = "/searchBySpeciality", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudentDTO>> getAllStudentsBySpeciality(@RequestParam("speciality") String speciality) {
        List<StudentDTO> student = studentService.getAllBySpeciality(speciality);
        if (CollectionUtils.isEmpty(student)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @ApiOperation(value = "Gets all students by group", nickname = "StudentController.getAllStudentsByGroup")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "StudentsByGroup")})
    @GetMapping(value = "/searchByGroupId" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudentDTO>> getAllStudentsByGroup(@RequestParam("groupId") Long groupId) {
        List<StudentDTO> student = studentService.getAllByGroup(groupId);
        if (CollectionUtils.isEmpty(student)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(student, HttpStatus.OK);    }
}
