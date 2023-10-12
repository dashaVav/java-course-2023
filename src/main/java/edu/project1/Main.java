package edu.project1;

final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary();
        ConsoleHangman newGame = new ConsoleHangman(dictionary);
        newGame.run();
    }
}
