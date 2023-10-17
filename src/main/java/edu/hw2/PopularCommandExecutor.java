package edu.hw2;

public final class PopularCommandExecutor {
    private final ConnectionManager manager;
    private final int maxAttempts;

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public void updatePackages() {
        tryExecute("apt update && apt upgrade -y");
    }

    void tryExecute(String command) {
        int attempt = 0;
        while (attempt < maxAttempts) {
            try (Connection connection = manager.getConnection()) {
                connection.execute("Connect");
                return;
            } catch (ConnectionException e) {
                attempt++;
                if (attempt == maxAttempts) {
                    throw new ConnectionException("Max attempts reached", e);
                }
            } catch (Exception e) {
                throw new ConnectionException("Closing error", e);
            }
        }
    }

//    public static void main(String[] args) {
//        ConnectionManager defaultManager = new ConnectionManager.DefaultConnectionManager();
//        ConnectionManager faultyManager = new ConnectionManager.FaultyConnectionManager();
//        PopularCommandExecutor executor = new PopularCommandExecutor(defaultManager, 10);
//        executor.updatePackages();
//    }

}
