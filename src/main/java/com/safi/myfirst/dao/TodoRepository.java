package com.safi.myfirst.dao;

import com.safi.myfirst.domaino.Todo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface TodoRepository extends CrudRepository<Todo, Long>
{
    @Query(value = "SELECT * FROM Todo t where t.text =:text", nativeQuery = true)
    public Optional<Todo> findOneByText(@Param("text") String text);

}
