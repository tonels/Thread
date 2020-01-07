package timeout;

public interface Publisher {
    PublishResult publish(Article article) throws InterruptedException;
}