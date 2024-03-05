package Bingo;

public class Main {
    public static void main(String[] args) {
        Thread thrd = new Thread(new BingoGame());
        thrd.start();
    }
}