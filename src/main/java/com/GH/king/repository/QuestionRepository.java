package com.GH.king.repository;

import com.GH.king.domain.Question;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by gy on 2016/7/19.
 */
public interface QuestionRepository extends CrudRepository<Question,String> {
}
