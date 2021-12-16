package tn.esprit.spring.service;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import net.bytebuddy.utility.RandomString;
import tn.esprit.spring.entity.Client;
import tn.esprit.spring.entity.CodePromo;
import tn.esprit.spring.repository.ClientRepository;
import tn.esprit.spring.repository.CodePromoRepository;
@Service
public class ServiceCodePromoImpl implements IserviceCodePromo {
	
	@Autowired
	CodePromoRepository corePromoRepo;
	@Autowired
	ClientRepository clientRepository;
	@Autowired
	private EmailSenderService emailService;

	@Override
	public List<CodePromo> retrieveAllCodes() {
		return corePromoRepo.findAll();
	}

	@Override
	public CodePromo addCodePromo(CodePromo c) {
		return corePromoRepo.save(c);
	}

	@Override
	public void deleteCodePromo(Long id) {
		 corePromoRepo.deleteById(id);
		
	}

	@Override
	public CodePromo updateCodePromo(CodePromo c) {
		return corePromoRepo.save(c);

	}

	@Override
	public CodePromo retrieveCodePromo(Long id) {
		return corePromoRepo.findById(id).orElse(null);
	}
	@Scheduled(fixedDelay = 10000)
	public void generatePromoCode() throws MessagingException {
		for(Client c: clientRepository.findAll()) {
			if(c.getCompteurPromo()>=1000) {
				CodePromo code= new CodePromo();
				code.setClient(c);
				code.setActive(true);
				RandomString r= new RandomString(8);
				String generatedString= r.make();
				code.setCode(generatedString);
				code.setPourcentageRemise(7);
				code.setMontantMin(100);
				Date now= new Date();
				now.setMonth(now.getMonth()+3);
				code.setDateExpiration(now);
				code.setCategorieProduit(clientRepository.getMostBoughtCategorie(c.getIdClient()));
				c.setCompteurPromo(c.getCompteurPromo()-1000);
				clientRepository.save(c);
				corePromoRepo.save(code);
				emailService.sendEmailWithAttachment("ahmedoussemabenhmida@icloud.com",
						"Votre CODE PROMO "+code.getCode(),
						"Congratulation !!!");
				
			}
		}
	}

}
