package com.cgpacalculator.CGPACalculator.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cgpacalculator.CGPACalculator.Entity.Subjects;

@Repository
public interface SubjectRepo extends JpaRepository<Subjects,Long>{

}
