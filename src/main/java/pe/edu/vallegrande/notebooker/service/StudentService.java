package pe.edu.vallegrande.notebooker.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.vallegrande.notebooker.model.Student;
import pe.edu.vallegrande.notebooker.repository.StudentRepository;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
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

    @Transactional
    public List<Student> removeAll(List<Long> ids) {
        studentRepository.removeAll(ids);
        return studentRepository.findAllById(ids);
    }

    @Transactional
    public Student updateStudent(Map<String, Object> updates, Long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);

        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();

            // Usa la reflexión para actualizar los campos
            Field[] fields = Student.class.getDeclaredFields();

            for (Field field : fields) {
                String fieldName = field.getName();
                if (updates.containsKey(fieldName)) {
                    try {
                        field.setAccessible(true);
                        field.set(student, updates.get(fieldName));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }

            return studentRepository.save(student);
        } else {
            return null;
        }
    }
}
