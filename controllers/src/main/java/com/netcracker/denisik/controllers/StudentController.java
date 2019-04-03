package com.netcracker.denisik.controllers;

import com.netcracker.denisik.dto.RoleDTO;
import com.netcracker.denisik.dto.StudentDTO;
import com.netcracker.denisik.services.implementations.StudentServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentServiceImpl studentService;

    @Autowired
    public StudentController(StudentServiceImpl studentService) {
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
    public ResponseEntity<StudentDTO> getStudent(@PathVariable("id") long id) {
        StudentDTO student = studentService.get(id);
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @ApiOperation(value = "Add student", nickname = "StudentController.addStudent")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Student is adding")})
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addStudent(@RequestBody StudentDTO student) {
        student.setRoleDTO(RoleDTO.STUDENT);
        long id = studentService.add(student);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update student", nickname = "StudentController.updateStudent")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Student update")})
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateStudent(@RequestBody StudentDTO student) {
        if (studentService.get(student.getId()) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (student.getWriteBook().getSemester().stream()
                .anyMatch(semesterDTO -> semesterDTO.getMark() >= 10 || semesterDTO.getMark() <= 0)) {
            return new ResponseEntity<>("Error in creation student", HttpStatus.BAD_REQUEST);
        }
        long id = studentService.add(student);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete student", nickname = "StudentController.deleteStudent")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Student is deleted")})
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteStudent(@PathVariable("id") Long id) {
        studentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
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
    @GetMapping(value = "/searchByGroupId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudentDTO>> getAllStudentsByGroup(@RequestParam("groupId") Long groupId) {
        List<StudentDTO> student = studentService.getAllByGroup(groupId);
        if (CollectionUtils.isEmpty(student)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @ApiOperation(value = "Gets all students by faculty", nickname = "StudentController.getAllStudentsByFaculty")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "StudentsByFaculty")})
    @GetMapping(value = "/searchByFaculty", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudentDTO>> getAllStudentsByFaculty(@RequestParam("faculty") String faculty) {
        List<StudentDTO> student = studentService.getAllByFaculty(faculty);
        if (CollectionUtils.isEmpty(student)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

//    @ApiOperation(value = "Gets TOP10 students by faculty", nickname = "StudentController.getTop10StudentsByFaculty")
//    @ApiResponses(value = {@ApiResponse(code = 200, message = "Top10StudentsByFaculty")})
//    @GetMapping(value = "/searchTop10ByFaculty", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<StudentDTO>> getTop10StudentsByFaculty(@RequestParam("faculty") String faculty) {
//        List<StudentDTO> student = studentService.getTop10ByFaculty(faculty);
//        if (CollectionUtils.isEmpty(student)) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(student, HttpStatus.OK);
//    }

}
