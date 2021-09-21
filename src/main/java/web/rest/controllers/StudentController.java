package web.rest.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.rest.models.Student;
import web.rest.repositories.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("/students")
@RestController
public class StudentController {

    private StudentRepository studentRepository;

    //Constructor Injection
    public StudentController(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    //HTTP GET (/students)
    @GetMapping
    public ResponseEntity<List<Student>> findAll(){

        List<Student> students = new ArrayList<>();
        studentRepository.findAll().forEach(students :: add);
        //ResponseEntity builder - først status (OK = 200) - til sidst body (students listen)
        return ResponseEntity.status(HttpStatus.OK).body(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Student>> findById(@PathVariable("id") long id){
        //Hent student fra repository
        Optional<Student> optionalStudent = studentRepository.findById(id);
        //Er student fundet?
        if(optionalStudent.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(optionalStudent);
        }
        //Not found
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(optionalStudent);
        }
    }

    //HTTP Post (/students) - create
    @CrossOrigin(origins = "*", exposedHeaders = "Location")
    @PostMapping(value = "")
    public ResponseEntity<Student> create(@RequestBody Student student){
        /*1. Opret ny student i JPA
        *      Location header /students/{id}
        *      location", "/students/" + newStudent.getId()
        *      HttpStatus.CREATED 201 */

        //Hvis id er sat, så returnerers BAD_REQUEST
        //Virker ikke ordentligt? sætter stadig ny student ind?
        if(student.getId() != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        //1
        Student newStudent = studentRepository.save(student);

        //Laver en string til location, frem for at skrive en længere linje nede i return.
        String location = "/students/" + newStudent.getId();

        //HttpStatus Created 201
        return ResponseEntity.status(HttpStatus.CREATED).header("Location", location).body(newStudent);

    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable("id") Long id, @RequestBody Student student){
        //Findes den studerende?
        Optional<Student> optionalStudent = studentRepository.findById(id);

        if (optionalStudent.isPresent()){
        //Er path id og student object id identiske? Ellers returnér BAD_REQUEST
            if(id.equals(student.getId())){
            //studerende findes, så der opdateres
                student.setId(id);
            studentRepository.save(student);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        else {
                //
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    //HTTP Delete (/students/{id}) - delete
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()){
            studentRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        else{
            //Not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
