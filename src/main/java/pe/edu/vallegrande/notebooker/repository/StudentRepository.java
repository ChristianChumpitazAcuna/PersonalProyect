package pe.edu.vallegrande.notebooker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pe.edu.vallegrande.notebooker.model.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query(value = "SELECT s FROM Student s WHERE s.status = :status ORDER BY s.last_name")
    List<Student> getAllByStatus(String status);

    @Modifying
    @Query(value = "UPDATE Student s SET s.status = 'A' WHERE s.id = :id")
    void restoreStudent(Long id);

    @Modifying
    @Query(value = "UPDATE Student s SET s.status = 'I' WHERE s.id = :id")
    void removeStudent(Long id);

    @Modifying
    @Query(value = "UPDATE Student s SET s.status = 'I' WHERE s.id IN :ids")
    void removeAll(List<Long> ids);
}
