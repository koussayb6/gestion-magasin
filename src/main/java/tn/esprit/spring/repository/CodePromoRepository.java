package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.CodePromo;
@Repository
public interface CodePromoRepository extends JpaRepository<CodePromo, Long>{

	CodePromo findByCode(String code);
}
