package model;

import controller.GraphicalCommandsView;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.GridLayout;
import javax.swing.JLabel;

public class GraphicalGridView extends JFrame {
    private final GraphicalCellView[][] plate;
    private int percent;
    private final int i;
    private final int j;
    private int remainingMines = 0;
    
    private JPanel pan1 = new JPanel();
    private JPanel pan2 = new JPanel();
    private JPanel pan3 = new JPanel();
    private JPanel empty1 = new JPanel();
    private JPanel empty2 = new JPanel();
    
    private JLabel mineRemaining = new JLabel();
    
    public GraphicalGridView(int i, int j, int percent) {
        this.i = i;
        this.j = j;
        this.plate = new GraphicalCellView[i][j];
        this.percent = percent;
        int surface = this.i * this.j;
        int nbMines = surface * this.percent / 100;
        GraphicalCommandsView nbMinesMarked = new GraphicalCommandsView();
        int nb_marked_mines = nbMinesMarked.getNbMarkedMines();
        remainingMines = nbMines - nb_marked_mines;
        
        this.setTitle("Démineur");
        this.setSize(700, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        
        
        setPlate(this.plate);
        setMines(this.percent);
        setNeighboors(this.plate);
        
        BorderLayout borderLayout = new BorderLayout(5, 5);
        GridLayout grid = new GridLayout(this.i, this.j, 3, 3);
        GridLayout text = new GridLayout(1, 3);
        
        pan2.setLayout(grid);
        pan1.setLayout(borderLayout);
        pan3.setLayout(text);
                
        pan1.add(pan2, BorderLayout.CENTER);
        pan1.add(pan3, BorderLayout.SOUTH);

        mineRemaining = new JLabel("Remaining mines : " + remainingMines);
        
        pan3.add(empty1);
        pan3.add(mineRemaining);
        pan3.add(empty2);
        
        System.out.println("Nouvelle grille graphique de démineur de " + this.getI() + " x " + this.getJ() + " avec " + percent + " mines.");
    }
    
    public void setPlate(GraphicalCellView[][] plate) {
        for(int x = 0; x < this.i; x++){
            for( int y = 0; y < this.j; y++){
                plate[x][y] = new GraphicalCellView();
            }
        }
    }
    
    public int getI() {
        return i;
    }
    
    public int getJ() {
        return j;
    }
    
    public void setMines(int percent){
        int surface = this.i * this.j;
        int nbMines = surface * this.percent / 100;
        int tempx;
        int tempy;
        for(int it = 0 ; it < nbMines ; it++){
            do{
                tempx = 0 + (int)(Math.random() * this.i);
                tempy = 0 + (int)(Math.random() * this.j);
            } while (!this.plate[tempx][tempy].getContent().equals(PossibleCell.EMPTY));
            this.plate[tempx][tempy].setContent(PossibleCell.MINE);
        }
    }
    
    public void setNeighboors(GraphicalCellView[][] plate){
        for(int x = 0 ; x < this.i ; x++){
            for(int y = 0 ; y < this.j ; y++){
                if(x-1 >= 0){
                    if(plate[x-1][y].getContent().equals(PossibleCell.MINE)){
                        plate[x][y].setNbNeighboors(plate[x][y].getNbNeighboors()+1);
                    }
                    if(y-1 >= 0){
                        if(plate[x-1][y-1].getContent().equals(PossibleCell.MINE)){
                            plate[x][y].setNbNeighboors(plate[x][y].getNbNeighboors()+1);
                        }
                    }
                    if(y+1 <= this.j-1){
                        if(plate[x-1][y+1].getContent().equals(PossibleCell.MINE)){
                            plate[x][y].setNbNeighboors(plate[x][y].getNbNeighboors()+1);
                        }
                    }
                }
                if(y-1 >= 0){
                    if(plate[x][y-1].getContent().equals(PossibleCell.MINE)){
                        plate[x][y].setNbNeighboors(plate[x][y].getNbNeighboors()+1);
                    }
                }
                if(x+1 <= this.i-1){
                    if(plate[x+1][y].getContent().equals(PossibleCell.MINE)){
                        plate[x][y].setNbNeighboors(plate[x][y].getNbNeighboors()+1);
                    }
                    if(y-1 >= 0){
                        if(plate[x+1][y-1].getContent().equals(PossibleCell.MINE)){
                            plate[x][y].setNbNeighboors(plate[x][y].getNbNeighboors()+1);
                        }
                    }
                    if(y+1 <= this.j-1){
                        if(plate[x+1][y+1].getContent().equals(PossibleCell.MINE)){
                            plate[x][y].setNbNeighboors(plate[x][y].getNbNeighboors()+1);
                        }
                    }
                }
                if(y+1 <= this.j-1){
                    if(plate[x][y+1].getContent().equals(PossibleCell.MINE)){
                        plate[x][y].setNbNeighboors(plate[x][y].getNbNeighboors()+1);
                    }
                }
            }
        }
    }
    
    public int getPercent() {
        return percent;
    }

    public GraphicalCellView[][] getPlate() {
        return plate;
    }
    

    public void setPercent(int percent) {
        this.percent = percent;
    }
    
    public void printDebug() {        
        for(int x = 0; x < this.i; x++) {
            for( int y = 0; y < this.j; y++) {
                if(this.plate[x][y].getContent() == PossibleCell.MINE){
                    pan2.add(new JButton("X"));
                } else {
                    if(this.plate[x][y].isMasked()){
                        pan2.add(new JButton(" "));
                    } else {
                        switch(this.plate[x][y].getNbNeighboors()){
                            case 0: this.getContentPane().add(new JButton("."));
                                    break;
                            case 1: this.getContentPane().add(new JButton("1"));
                                    break;
                            case 2: this.getContentPane().add(new JButton("2"));
                                    break;
                            case 3: this.getContentPane().add(new JButton("3"));
                                    break;
                            case 4: this.getContentPane().add(new JButton("4"));
                                    break;
                            case 5: this.getContentPane().add(new JButton("5"));
                                    break;
                            case 6: this.getContentPane().add(new JButton("6"));
                                    break;
                            case 7: this.getContentPane().add(new JButton("7"));
                                    break;
                            case 8: this.getContentPane().add(new JButton("8"));
                                    break;
                        }
                    }
                }
            }
        }
        
        this.setContentPane(pan1);
        this.setVisible(true);
    }
    
    public void print(){
       int nbCell = this.getI() * this.getJ();
       
       for(int i = 0; i < nbCell; i++) {
           pan2.add(new JButton(" "));
       }
       
       this.setContentPane(pan1);
        this.setVisible(true);
        
        /*for(int x = 0 ; x < this.i ; x++){
            for(int y = 0 ; y < this.j ; y++){
                if(this.plate[x][y].getMark() == Marks.MARKED_MINE){
                    System.out.print("! ");
                } else if(this.plate[x][y].getMark() == Marks.MARKED_UNKNOWN){
                    System.out.print("? ");
                } else {
                    if(this.plate[x][y].isMasked()){
                        System.out.print("# ");
                    } else {
                        if(this.plate[x][y].getContent() == PossibleCell.MINE){
                            System.out.print("X ");
                        } else {
                            switch(this.plate[x][y].getNbNeighboors()){
                                case 0: System.out.print(". ");
                                        break;
                                case 1: System.out.print("1 ");
                                        break;
                                case 2: System.out.print("2 ");
                                        break;
                                case 3: System.out.print("3 ");
                                        break;
                                case 4: System.out.print("4 ");
                                        break;
                                case 5: System.out.print("5 ");
                                        break;
                                case 6: System.out.print("6 ");
                                        break;
                                case 7: System.out.print("7 ");
                                        break;
                                case 8: System.out.print("8 ");
                                        break;
                            }
                        }
                    }
                }
            }
            System.out.println("");
        }*/
    }
}