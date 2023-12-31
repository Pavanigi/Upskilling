package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {
	@Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{name}")
    public ResponseEntity<Student> getStudentByName(@PathVariable String name) {
        Optional<Student> studentOptional = studentService.getStudentByName(name);
        return studentOptional.map(ResponseEntity::ok)
                              .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/name/a")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student createdStudent = studentService.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }

    @PutMapping("/{name}/b")
    public ResponseEntity<Student> updateStudent(@PathVariable String name, @RequestBody Student updatedStudent) {
        Optional<Student> updatedStudentOptional = studentService.updateStudent(name, updatedStudent);

        return updatedStudentOptional.map(ResponseEntity::ok)
                                     .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{name}/c")
    public ResponseEntity<String> deleteStudent(@PathVariable String name) {
        boolean deleted = studentService.deleteStudent(name);

        if (deleted) {
            return ResponseEntity.ok("Student with name '" + name + "' has been deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Student with name '" + name + "' not found.");
        }
    }

}



