package com.indocyber.demo.repository;

import com.indocyber.demo.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String > {
}
