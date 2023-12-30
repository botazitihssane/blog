package ma.emsi.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ma.emsi.blog.exception.DuplicateUserException;
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
		boolean usernameExists = userRepository.existsByUsername(p.getUsername());
		boolean emailExists = userRepository.existsByEmail(p.getEmail());

		if (usernameExists && emailExists) {
			throw new DuplicateUserException("Error: Username and Email are already taken!");
		} else if (usernameExists) {
			throw new DuplicateUserException("Error: Username is already taken!");
		} else if (emailExists) {
			throw new DuplicateUserException("Error: Email is already in use!");
		}

		return true;
	}

	@Override
	public boolean existsById(int id) {
		return findById(id) != null;
	}
}
