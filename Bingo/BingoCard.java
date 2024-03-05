package Bingo;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BingoCard {
    private final int[][] numbers;
    private final int id;

    public BingoCard(int id){
        numbers = new int[5][5];
        this.id = id;
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                if(i == 2 && j == 2){
                    numbers[i][j] = 0;
                    continue;
                }
                Random random = new Random();
                int toAdd;
                while(true){
                    toAdd = (random.nextInt((i*15)+1, ((i+1)*15)+1));
                    boolean isUnique = true;
                    for(int k=0; k<5; k++){
                        if(numbers[i][k] == toAdd){
                            isUnique = false;
                            break;
                        }
                    }
                    if(isUnique){
                        numbers[i][j] = toAdd;
                        break;
                    }
                }
            }
        }
    }

    public int getId(){
        return id;
    }

    public int[][] getNumbers() {
        return numbers;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }

    public void print(){
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                System.out.print(numbers[j][i] + "\t");
            }
            System.out.println();
        }
    }
}