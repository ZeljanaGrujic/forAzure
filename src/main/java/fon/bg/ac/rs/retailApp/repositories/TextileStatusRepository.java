package fon.bg.ac.rs.retailApp.repositories;

import fon.bg.ac.rs.retailApp.models.TextileStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextileStatusRepository extends JpaRepository<TextileStatus, Integer> {
}
