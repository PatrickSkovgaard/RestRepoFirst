package web.rest.repositories;

import org.springframework.data.repository.CrudRepository;
import web.rest.models.Student;


public interface StudentRepository extends CrudRepository<Student, Long> {


}
