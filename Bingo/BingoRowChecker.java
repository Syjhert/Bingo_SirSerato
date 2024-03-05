package Bingo;

public class BingoRowChecker extends BingoChecker{
    private int row;

    public BingoRowChecker(BingoCard card, int row) {
        super(card);
        this.row = row-1;
    }

    @Override
    public void run() {
        boolean done = false;
        for(int i=0; i<5; i++){
            int num = card.getNumbers()[row][i];
            if(num == 0) continue;

            while(!BingoGame.results[num] && !BingoGame.bingo){
                try {
                    synchronized (BingoGame.results){
                        BingoGame.results.wait();
                    }
                } catch (InterruptedException e) {

                }
            }
            if(BingoGame.bingo) break;
            if(i==4) done = true;
        }
        if(done) System.out.println("Card" + card + " - completes row " + (row+1));
    }
}