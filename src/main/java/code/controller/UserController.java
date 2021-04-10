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
@CrossOrigin(origins = {"https://code-block-academy.netlify.app", "http://localhost:3000"})
@RequestMapping("/student")
public class UserController {

    final StudentRepository studentRepository;

    @Autowired
    public UserController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /* Create */
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createStudent(@RequestBody Student student){
        studentRepository.save(student);
        return new ResponseEntity<>(msg("User Create"), HttpStatus.CREATED);
    }

    @PostMapping("/student/{id}/add-resource")
    public ResponseEntity<Map<String, Object>> addResource(@PathVariable String id, Map<String, Object> resource){
        Student student = studentRepository.findById(id).orElse(null);
        if (student != null){
            student.addResource(resource);
            return new ResponseEntity<>(msg("Added Resource"), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(msg("Some Problem"), HttpStatus.I_AM_A_TEAPOT);
        }
    }

    @PostMapping("/student/{id}/add-content")
    public ResponseEntity<Map<String, Object>> addContent(@PathVariable String id, Map<String, Object> content){
        Student student = studentRepository.findById(id).orElse(null);
        if (student != null){
            student.addContent(content);
            return new ResponseEntity<>(msg("Added content"), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(msg("Some Problem"), HttpStatus.I_AM_A_TEAPOT);
        }
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
            return new ResponseEntity<>(msg("Student does not exist"), HttpStatus.I_AM_A_TEAPOT);
        }
    }

    /* Update */

    /* Delete */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteStudent(@PathVariable String id){
        studentRepository.deleteById(id);
        return ResponseEntity.ok(msg("User Delete"));
    }

    /* Functions Aux */
    private Map<String, Object> msg(String msg){
        Map<String, Object> message = new HashMap<>();
        message.put("msg", msg);
        return message;
    }
}
