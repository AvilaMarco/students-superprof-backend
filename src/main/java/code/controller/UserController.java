package code.controller;

import code.models.Student;
import code.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
//@CrossOrigin("")
@RequestMapping("/student")
public class UserController {
    //Repository
    @Autowired
    StudentRepository studentRepository;

    /* Create */
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createStudent(@RequestBody Student student){
        return new ResponseEntity<>(studentRepository.save(student).studentDTO(), HttpStatus.CREATED);
    }

    /* Read */
    @GetMapping("/students")
    public ResponseEntity<List<Map<String, Object>>> getStudents(){
        return new ResponseEntity<>(studentRepository.findAll().stream().map(Student::studentDTO).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Map<String, Object>> getStudent(@PathVariable String id){
        Student student = studentRepository.findById(id).orElse(null);
        if (student != null){
            return new ResponseEntity<>(student.studentCompleteDTO(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(msgError("Student does not exist"), HttpStatus.I_AM_A_TEAPOT);
        }
    }

    /* Update */

    /* Delete */

    /* Functions Aux */
    private Map<String, Object> msgError(String msg){
        Map<String, Object> msgError = new HashMap<>();
        msgError.put("error", msg);
        return msgError;
    }
}
