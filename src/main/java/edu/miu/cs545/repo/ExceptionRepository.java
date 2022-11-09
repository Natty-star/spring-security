package edu.miu.cs545.repo;

import edu.miu.cs545.domain.ExceptionDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExceptionRepository extends JpaRepository<ExceptionDb,Long> {
}
