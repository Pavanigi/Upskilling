package net.javaguides.springboot.controller;

import net.javaguides.springboot.bean.Student;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    // http://localhost:8080/student
    @GetMapping("student")
    public Student getStudent(){
        Student student = new Student(
          1,
          "Pavani",
          "Pallapu"
        );
        return student;
    }

    
    @GetMapping("students")
    public List<Student> getStudents(){
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Ramesh", "Fadatare"));
        students.add(new Student(2, "Pavani", "Pallapu"));
        students.add(new Student(3, "Ram", "Jadhav"));
        students.add(new Student(4, "Sanjay", "Pawar"));
        return students;
    }

    
    @GetMapping("students/{id}/{first-name}/{last-name}")
    public Student studentPathVariable(@PathVariable("id") int studentId,
                                       @PathVariable("first-name") String firstName,
                                       @PathVariable("last-name") String lastName){
        return new Student(studentId, firstName, lastName);
    }

    
    @GetMapping("students/query")
    public Student studentRequestVariable(@RequestParam int id,
                                          @RequestParam String firstName,
                                          @RequestParam String lastName){
        return new Student(id, firstName, lastName);
    }
    
     @PostMapping("students/create")
     @ResponseStatus(HttpStatus.CREATED)
     public Student createStudent(@RequestBody Student student) {
    	 System.out.println(student.getId());
    	 System.out.println(student.getFirstName());
    	 System.out.println(student.getLastName());
    	 return student;
     }
     
     
     @PutMapping("students/{id}/update")
     public Student updateStudent(@RequestBody Student student,@PathVariable("id") int studentId) {
    	 System.out.println(student.getFirstName());
    	 System.out.println(student.getLastName());
    	 return student;
     }
     
}