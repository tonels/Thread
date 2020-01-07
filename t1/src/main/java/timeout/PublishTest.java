//package timeout;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//import java.util.concurrent.*;
//import java.util.stream.Collectors;
//
//public class PublishTest {
//
//    private Publisher publisher;
//
//    public PublishTest(Publisher publisher) {
//        this.publisher = publisher;
//    }
//    private List<Article> list;
//
//    @BeforeEach
//    void setUp() {
//        for (int i = 0; i < 50; i++) {
//            Article article = new Article(i, "Title" + i);
//            list.add(article);
//        }
//    }
//
//    @Test
//    public void t1() throws ExecutionException, InterruptedException {
////        List<PublishResult> publishResults = this.publishArticles(list);
////        publishResults.forEach(System.out::println);
//        System.out.println("ss");
//
//    }
//
//    public List<PublishResult> publishArticles(List<Article> articles) throws InterruptedException, ExecutionException {
//
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//
//        return articles.stream()
//                .map(article -> {
//                    Future<PublishResult> task = executorService.submit(() -> publisher.publish(article));
//                    try {
//                        return task.get(10, TimeUnit.SECONDS);
//                    } catch (InterruptedException | ExecutionException | TimeoutException e) {
//                        return PublishResult.FAILED(article);
//                    }
//                })
//                .collect(Collectors.toList());
//    }
//
//}
