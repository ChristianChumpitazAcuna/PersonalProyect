package pe.edu.vallegrande.notebooker.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.vallegrande.notebooker.model.Student;
import pe.edu.vallegrande.notebooker.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    public StudentRepository studentRepository;

    public List<Student> getActives() {
        return studentRepository.getAllByStatus("A");
    }

    public List<Student> getInactives() {
        return studentRepository.getAllByStatus("I");
    }

    @Transactional
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Transactional
    public List<Student> saveAll(List<Student> students) {
        return studentRepository.saveAll(students);
    }

    @Transactional
    public Optional<Student> delete(Long id) {
        studentRepository.removeStudent(id);
        return studentRepository.findById(id);
    }

    @Transactional
    public Optional<Student> restore(Long id) {
        studentRepository.restoreStudent(id);
        return studentRepository.findById(id);
    }
}
