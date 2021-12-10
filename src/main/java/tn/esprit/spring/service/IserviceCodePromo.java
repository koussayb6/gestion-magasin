package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.CodePromo;

public interface IserviceCodePromo {
	List<CodePromo> retrieveAllCodes();

	CodePromo addCodePromo(CodePromo c);

	void deleteCodePromo(Long id);

	CodePromo updateCodePromo(CodePromo c);

	CodePromo retrieveCodePromo(Long id);
}
