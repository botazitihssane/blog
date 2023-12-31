package ma.emsi.blog.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.emsi.blog.model.Article;
import ma.emsi.blog.model.Commentaire;
import ma.emsi.blog.repository.ArticleRepository;
import ma.emsi.blog.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleRepository articleRepository;

	@Override
	public void create(Article a) {
		articleRepository.save(a);
	}

	@Override
	public void update(Article a) {
		Article newArticle = findById(a.getId());			System.out.println(newArticle);
		System.out.println(a);
		if (newArticle != null) {
			System.out.println(newArticle);
			newArticle.setCategorie(a.getCategorie());
			newArticle.setDate(a.getDate());
			newArticle.setLien(a.getLien());
			newArticle.setNombreCommentaire(a.getNombreCommentaire());
			newArticle.setPhoto(a.getPhoto());
			newArticle.setTexte(a.getTexte());
			newArticle.setTitre(a.getTitre());
			articleRepository.save(newArticle);
		}
	}

	@Override
	public void delete(int id) {
		articleRepository.deleteById(id);
	}

	@Override
	public List<Article> findAll() {
		return articleRepository.findAll();
	}

	@Override
	public Article findById(int id) {
		return articleRepository.findById(id);
	}

	@Override
	public List<Article> sortByDate() {
		return articleRepository.sortByDate();
	}

	@Override
	public List<Article> sortByComments() {
		return articleRepository.sortByComments();
	}

	@Override
	public List<Article> findByDate(LocalDate date) {
		return articleRepository.findByDate(date);
	}

	@Override
	public List<Article> searchByKeyword(String keyword) {
		return articleRepository.searchByKeyword(keyword);
	}

	@Override
	public List<Article> searchByCategory(int id) {
		return articleRepository.findByCategory(id);
	}

	@Override
	public void addCommentaire(Commentaire c) {
		Article article = findById(c.getArticle().getId());
		if (article != null) {
			article.setNombreCommentaire(article.getNombreCommentaire() + 1);
			articleRepository.save(article);
		}
	}

	@Override
	public void deleteCommentaire(Commentaire c) {
		Article article = findById(c.getArticle().getId());
		if (article != null) {
			article.setNombreCommentaire(article.getNombreCommentaire() - 1);
			articleRepository.save(article);
		}
	}

}
