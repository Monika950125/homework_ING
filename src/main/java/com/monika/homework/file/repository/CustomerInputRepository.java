package com.monika.homework.file.repository;

import com.monika.homework.file.domain.CustomerInput;
import com.monika.homework.file.domain.ProcessingStatus;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface CustomerInputRepository extends CrudRepository<CustomerInput, Long> {

    @Query
    boolean existsByInput(String input);

    @Query
    List<CustomerInput> findAllByStatus(ProcessingStatus status);

    @Transactional
    @Modifying
    @Query("update CustomerInput input set input.status = :status where input.id = :id")
    void updateStatus(@Param("status") ProcessingStatus status, Long id);

    @Transactional
    @Modifying
    @Query("update CustomerInput input set input.exception = :exception, input.status = :status where input.id = :id")
    void updateStatusAndException(@Param("exception") String exception, @Param("status") ProcessingStatus status, Long id);

}
