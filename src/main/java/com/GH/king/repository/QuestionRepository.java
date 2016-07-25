package com.GH.king.repository;

import com.GH.king.domain.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by gy on 2016/7/19.
 */
public interface QuestionRepository extends CrudRepository<Question,String> {
    @Query("select q from Question q where(q.group = :group) order by newId()")
    Page<Iterable<Question>> getRandQuestions(@Param("group")String group , Pageable p);
}
