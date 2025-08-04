package comsip.ams.Services;
import java.util.List;
import java.util.Optional;
import comsip.ams.Entities.Article;
import comsip.ams.dto.ArticleDTO;

public interface ArticleService {
    List<Article> getAllArticles();
    Article saveArticle(Article article);
    Optional<Article> getArticleById(int id);
    void deleteArticleById(int id);
    public Article updateArticle(ArticleDTO articleModel);
}
