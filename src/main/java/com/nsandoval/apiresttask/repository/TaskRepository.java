
package com.nsandoval.apiresttask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nsandoval.apiresttask.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
