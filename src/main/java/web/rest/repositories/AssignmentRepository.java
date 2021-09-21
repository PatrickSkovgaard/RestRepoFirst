package web.rest.repositories;

import org.springframework.data.repository.CrudRepository;
import web.rest.models.Assignment;

public interface AssignmentRepository extends CrudRepository<Assignment, Long> {
}
