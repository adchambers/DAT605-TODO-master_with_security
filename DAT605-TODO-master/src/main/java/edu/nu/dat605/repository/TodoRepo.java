package edu.nu.dat605.repository;

import edu.nu.dat605.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ZS13478 on 9/29/2016.
 */
public interface TodoRepo extends JpaRepository<Todo, Integer> {

}
