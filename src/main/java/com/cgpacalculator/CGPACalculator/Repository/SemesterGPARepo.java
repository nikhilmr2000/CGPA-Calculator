package com.cgpacalculator.CGPACalculator.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cgpacalculator.CGPACalculator.Entity.SemesterGPA;

@Repository
public interface SemesterGPARepo extends JpaRepository<SemesterGPA,Long>{

}
