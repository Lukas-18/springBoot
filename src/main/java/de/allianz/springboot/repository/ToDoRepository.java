package de.allianz.springboot.repository;

import de.allianz.springboot.entity.ToDo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoRepository  extends CrudRepository<ToDo, Long> {

    //derived methods = abgeleitete Methoden, im Hintergrund Java Methode wird übersetzt in SQL Statement zur Laufzeit
    List<ToDo> findAllByIsDoneIsTrue();

    List<ToDo> findAllByIsDoneIsFalse();


    //Im Hintergrund hinter der derived method passiert folgendes:
    //@Query(value = "SELECT COUNT(*) FROM to_do WHERE to_do.status = true", nativeQuery = true)
    //Long manualCount();
    Long countAllByIsDoneIsTrue();



    //generischer Ansatz:
    // (Also anstatt: "Long countAllByIsDoneIsTrue();" & "Long countAllByIsDoneIsFalse();")
    Long countAllByIsDone(Boolean staus);

}
