package com.indocyber.demo.repository;

import com.indocyber.demo.dto.customer.CustomerGridDTO;
import com.indocyber.demo.entity.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, String> {

    @Query("""
			SELECT new com.indocyber.demo.dto.customer.CustomerGridDTO(cus.membershipNumber, CONCAT(cus.firstName, ' ', cus.lastName),
				cus.membershipExpiredDate)
			FROM Customer AS cus
			WHERE cus.membershipNumber LIKE %:membershipNumber%
            	AND (:fullName = '' OR CONCAT(cus.firstName, ' ', cus.lastName) LIKE CONCAT('%', :fullName, '%'))
			""")
    List<CustomerGridDTO> findAll(String membershipNumber, String fullName, Pageable pagination);


	@Query("""
			SELECT COUNT(cus.id)
			FROM Customer AS cus
			WHERE cus.membershipNumber LIKE %:membershipNumber%
            	AND (:fullName = '' OR CONCAT(cus.firstName, ' ', cus.lastName) LIKE CONCAT('%', :fullName, '%'))
			""")
	Long count(String membershipNumber, String fullName);
}
