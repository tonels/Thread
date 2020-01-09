package timeout;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PublishTest {
    private List<Article> list;

    @BeforeEach
    void setUp() {
        List<Article> list = Lists.newArrayList();
        for (int i = 0; i < 50; i++) {
            Article article = new Article(i, "Title" + i);
            list.add(article);
        }
        this.list = list;
    }


    @Test
    public void testPublisherTimeout() throws Exception {

        PublishArticlesUseCase useCase = new PublishArticlesUseCase(article -> {
            Thread.sleep(1000);
            return PublishResult.OK(article);
        });

        List<PublishResult> result = useCase.publishArticles(list);
        result.stream().map(e -> e.getStatus()).forEach(System.out::println);
//        assertEquals(result.get(0).getStatus(), PublishResult.Status.FAILED);
    }

    @Test
    public void testPublisherOk() throws Exception {

        PublishArticlesUseCase useCase = new PublishArticlesUseCase(article -> PublishResult.OK(article));

        List<Article> articles = Arrays.asList(new Article(3, "Java rises again"));
        List<PublishResult> result = useCase.publishArticles(articles);

        assertEquals(result.get(0).getStatus(), PublishResult.Status.OK);
    }


}
