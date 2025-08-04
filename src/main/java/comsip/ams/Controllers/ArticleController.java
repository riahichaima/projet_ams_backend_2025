package comsip.ams.Controllers;

import comsip.ams.Entities.Article;
import comsip.ams.Services.ArticleService;
import comsip.ams.dto.ArticleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/articles")
@CrossOrigin(origins = "*") // Autorise les requêtes depuis n'importe quel frontend (utile pour Angular, React...)
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    // ✅ GET all articles
    @GetMapping
    public List<Article> getAllArticles() {
        return articleService.getAllArticles();
    }

    // ✅ GET article by ID
    @GetMapping("/{id}")
    public Optional<Article> getArticleById(@PathVariable int id) {
        return articleService.getArticleById(id);
    }

    // ✅ POST new article
    @PostMapping
    public Article createArticle(@RequestBody Article article) {
        return articleService.saveArticle(article);
    }




    // ✅ DELETE article
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable int id) {
        articleService.deleteArticleById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping()
    public ResponseEntity<Article> updateArticle(@RequestBody ArticleDTO articleDto){

        return new ResponseEntity<>(this.articleService.updateArticle(articleDto), HttpStatus.CREATED);

    }

}
