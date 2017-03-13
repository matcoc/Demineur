package controller;

import java.util.Scanner;
import model.*;

/**
 *
 * @author pa
 */

public class Game {
    private final Init initialisation;
    private final Grid grid;
    private final Scanner sc;
    
    
    public Game(){
        this.initialisation = new Init();
        this.grid = new Grid(initialisation.getLine(), initialisation.getRow(), initialisation.getPercent());
        sc = new Scanner(System.in);
        //this.grid.print();
        this.grid.printDebug();
    }
    
    public boolean isSuccessful(){
        boolean successful = true;
        
        for(int i = 0 ; i < this.initialisation.getLine() ; i++){
            for(int j = 0 ; j < this.initialisation.getRow() ; j++){
                if(this.grid.getPlate()[i][j].isMasked() && this.grid.getPlate()[i][j].getContent() == PossibleCell.EMPTY){
                    successful = false;
                }
            }
        }
        
        if(successful){
            System.out.println("Bravo ! C'est gagné !");
        }
        
        return successful;
    }
    
    public boolean isLost(){
        boolean lost  = false;
        
        for(int i = 0 ; i < this.initialisation.getLine() ; i++){
            for(int j = 0 ; j < this.initialisation.getRow() ; j++){
                if(!this.grid.getPlate()[i][j].isMasked() && this.grid.getPlate()[i][j].getContent() == PossibleCell.MINE){
                    lost = true;
                }
            }
        }
        
        if(lost){
            System.out.println("PERDU");
        }
        
        return lost;
    }
    
    public void round(){
        String str = sc.nextLine();
        
    }
}