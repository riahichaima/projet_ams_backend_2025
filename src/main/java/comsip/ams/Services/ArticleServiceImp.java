package comsip.ams.Services;

import comsip.ams.Entities.Article;
import comsip.ams.Entities.Provider;
import comsip.ams.dto.ArticleDTO;
import comsip.ams.repositories.ArticleRepository;
import comsip.ams.repositories.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImp implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private ProviderRepository providerRepository;

    @Override
    public List<Article> getAllArticles() {
        List<Article> list = new ArrayList<>();
        articleRepository.findAll().forEach(list::add);
        return list;
    }

    @Override
    public Article saveArticle(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public Optional<Article> getArticleById(int id) {
        return articleRepository.findById(id);
    }

    @Override
    public void deleteArticleById(int id) {
        articleRepository.deleteById(id);
    }


    @Override
    public Article updateArticle(ArticleDTO articleModel) {
        int idArticle = articleModel.getId();
        Optional<Article> optArticle = this.getArticleById(idArticle);

        Optional<Provider> optProvider = this.providerRepository.findById(articleModel.getIdProvider());

        Article article = optArticle.orElseThrow(() -> new RuntimeException("Article not found"));
        Provider provider = optProvider.orElseThrow(() -> new RuntimeException("Provider not found"));


        article.setLibelle(articleModel.getLibelle());
        article.setPrice(articleModel.getPrice());
        article.setProvider(provider);
        articleRepository.save(article);
        return article;
    }


}
