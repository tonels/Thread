package timeout;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class PublishArticlesUseCase {
    private Publisher publisher;

    public PublishArticlesUseCase(Publisher publisher) {
        this.publisher = publisher;
    }

    public List<PublishResult> publishArticles(List<Article> articles) throws InterruptedException, ExecutionException {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        return articles.stream()
                .map(article -> {
                    Future<PublishResult> task = executorService.submit(() -> publisher.publish(article));
                    try {
                        return task.get(2, TimeUnit.SECONDS);
                    } catch (InterruptedException | ExecutionException | TimeoutException e) {
                        return PublishResult.FAILED(article);
                    }
                })
                .collect(Collectors.toList());

//        return articles.stream()
//                .map(new Function<Article, PublishResult>() {
//                    @Override
//                    public PublishResult apply(Article article) {
//                        Future<PublishResult> task = executorService.submit(new Callable<PublishResult>() {
//                        @Override
//                        public PublishResult call() throws Exception {
//                            return publisher.publish(article);
//                        }
//                    });
//                    try {
//                        return task.get(10, TimeUnit.SECONDS);
//                    } catch (InterruptedException | ExecutionException | TimeoutException e) {
//                        return PublishResult.FAILED(article);
//                    }
//                })
//                .collect(Collectors.toList());
    }

}
