package com.ashraya.supplier.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashraya.supplier.constants.State;
import com.ashraya.supplier.model.Questions;;

@Repository
public interface QuestionsRepository extends JpaRepository<Questions, Integer>{

	public List<Questions> findQuestionsByState(Enum<State> state);
}
