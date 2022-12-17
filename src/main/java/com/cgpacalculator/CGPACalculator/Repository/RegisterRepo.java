package com.cgpacalculator.CGPACalculator.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cgpacalculator.CGPACalculator.Entity.register;

@Repository
public interface RegisterRepo extends JpaRepository<register,Long>{
	
}
