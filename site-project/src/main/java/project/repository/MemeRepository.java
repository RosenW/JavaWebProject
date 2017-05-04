package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.entities.Meme;
import project.entities.User;

import java.util.List;

@Repository
public interface MemeRepository extends JpaRepository<Meme, Long>{
    @Query("SELECT m FROM Meme as m WHERE m.user = :user")
    List<Meme> findAllByUser(@Param("user") User user);
}
