package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.entities.Type;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long>{
    Type findOneByName(String type);
}
