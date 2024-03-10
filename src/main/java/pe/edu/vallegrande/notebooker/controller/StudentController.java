package pe.edu.vallegrande.notebooker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.vallegrande.notebooker.model.Student;
import pe.edu.vallegrande.notebooker.service.StudentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    public StudentService studentService;

    @GetMapping("/getActives")
    public ResponseEntity<List<Student>> getActives() {
        return ResponseEntity.ok(studentService.getActives());
    }

    @GetMapping("/getInactives")
    public ResponseEntity<List<Student>> getInactives() {
        return ResponseEntity.ok(studentService.getInactives());
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Student student) {
        Student saveStudent = studentService.save(student);
        return ResponseEntity.ok(saveStudent);
    }

    @PostMapping("/insertAll")
    public ResponseEntity<?>saveAll (@RequestBody List<Student>students){
        List<Student> saveStudents = studentService.saveAll(students);
        return ResponseEntity.ok(saveStudents);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Optional<Student>> delete(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.delete(id));
    }

    @PatchMapping("/restore/{id}")
    public ResponseEntity<Optional<Student>> restore(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.restore(id));
    }
}
