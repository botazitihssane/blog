package ma.emsi.blog.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ma.emsi.blog.model.Proprietaire;
import ma.emsi.blog.repository.ProprietaireRepository;
import ma.emsi.blog.repository.UtilisateurRepository;
import ma.emsi.blog.service.ProprietaireService;

@Service
public class ProprietaireServiceImpl implements ProprietaireService {
	@Autowired
	private ProprietaireRepository proprietaireRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UtilisateurRepository userRepository;

	@Override
	public void create(Proprietaire p) {
		if (validateAndCheckUniqueness(p)) {
			p.setPassword(passwordEncoder.encode(p.getPassword()));
			proprietaireRepository.save(p);
		}
	}

	@Override
	public void update(Proprietaire p) {
		if (validateAndCheckUniqueness(p)) {
			Proprietaire existingProprietaire = findById(p.getId());
			if (existingProprietaire != null) {
				existingProprietaire.setUsername(p.getUsername());
				existingProprietaire.setBiographie(p.getBiographie());
				existingProprietaire.setPhoto(p.getPhoto());
				existingProprietaire.setEmail(p.getEmail());
				existingProprietaire.setPassword(passwordEncoder.encode(p.getPassword()));
				proprietaireRepository.save(existingProprietaire);
			}
		}
	}

	@Override
	public void delete(int id) {
		proprietaireRepository.deleteById(id);
	}

	@Override
	public List<Proprietaire> findAll() {
		return proprietaireRepository.findAll();
	}

	@Override
	public Proprietaire findById(int id) {
		return proprietaireRepository.findById(id);
	}

	private boolean validateAndCheckUniqueness(Proprietaire p) {
		if (userRepository.existsByUsername(p.getUsername())) {
			throw new RuntimeException("Error: Username is already taken!");
		}

		if (userRepository.existsByEmail(p.getEmail())) {
			throw new RuntimeException("Error: Email is already in use!");
		}

		return true;
	}

	@Override
	public boolean existsById(int id) {
		Proprietaire existingProprietaire = findById(id);
		if (existingProprietaire != null) {
			return true;
		}
		return false;
	}
}
