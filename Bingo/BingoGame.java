package Bingo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BingoGame implements Runnable{
    public static boolean[] results;
    public static boolean bingo;
    private List<BingoCard> bingoCards;

    public BingoGame(){
        results = new boolean[76];
        bingo = false;
        bingoCards = new ArrayList<>();
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        System.out.print("How many players/cards: ");
        int size = sc.nextInt();
        for(int i=0; i<size; i++){
            bingoCards.add(new BingoCard(BingoIDMaker.getNewID()));
        }
        for(BingoCard card : bingoCards){
            System.out.println("ID: " + card.getId());
            card.print();
            System.out.println();
        }
        List<Thread> threads = new ArrayList<>();

        System.out.println("What pattern? 1.+ 2.#");
        int pattern = sc.nextInt();
        if(pattern == 1){
            for(BingoCard card : bingoCards){
                threads.add(new Thread(new BingoPatternPlus(card)));
            }
        }else{
            for(BingoCard card : bingoCards){
                threads.add(new Thread(new BingoPatternHash(card)));
            }
        }

        for(Thread thrd : threads){
            thrd.start();
        }

        int tempRes;
        while(!bingo){
            while(true){
                tempRes = rand.nextInt(1, 76);
                if(!results[tempRes]) break;
            }
            System.out.println("----------------------------------");
            System.out.println("The number is: " + tempRes);
            System.out.println("----------------------------------");
            printResults();
            System.out.println();
            results[tempRes] = true;

            synchronized (results){
                results.notifyAll();
            }

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
            }
        }

        synchronized (results){ //another notify to wake up waiting threads and terminate them
            results.notifyAll();
        }
    }

    private void printResults(){
        System.out.print("Rolled numbers: ");
        for(int i=0; i<76; i++){
            if(results[i]) System.out.print(i + " ");
        }
        System.out.println();
    }
}