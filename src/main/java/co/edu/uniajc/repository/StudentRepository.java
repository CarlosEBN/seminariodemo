package co.edu.uniajc.repository;

import co.edu.uniajc.model.StudentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<StudentModel, Long> {

    List<StudentModel> findAllByNameContains(String name);

    Optional<StudentModel> findById(Long id);

    List<StudentModel> findAllByAge(Integer age);
}
