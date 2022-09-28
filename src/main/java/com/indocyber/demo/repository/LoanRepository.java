package com.indocyber.demo.repository;

import com.indocyber.demo.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    @Query("""
			SELECT COUNT(lo.id) 
			FROM Loan AS lo
			WHERE lo.customerNumber = :membershipNumber
			""")
    Long countByCustomer(String membershipNumber);
}
