package tonels.bank;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Repository {

    private static Repository instance;
    private List<Account> accounts;

    private Repository() {
        int count = (int) (Math.random() * 1000 + 4000);
        accounts = new CopyOnWriteArrayList<>();
        for (int i = 0; i < count; i++) {
            accounts.add(Account.generateFakeAccount());
        }
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public static Repository getInstance() {
        synchronized (Repository.class) {
            if (instance == null) {
                instance = new Repository();
            }
        }

        return instance;
    }
}
