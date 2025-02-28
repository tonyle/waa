package edu.miu.lab1.repo;

import edu.miu.lab1.entity.Exception;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExceptionRepo extends JpaRepository<Exception, Long> {

}
