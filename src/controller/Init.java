package controller;

import java.util.Scanner;

public class Init {
    
    private int row;
    private int line;
    private int percent;
    
    public Init() {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter number of row : ");
        row = sc.nextInt();
        while(row <= 0) {
            System.out.println("Number of row must be > 0, please enter a new number of row :");
            row = sc.nextInt();
        }
        setRow(row);
        
        System.out.println("Enter number of line : ");
        line = sc.nextInt();
        while(line <= 0) {
            System.out.println("Number of line must be > 0, please enter a new number of line :");
            line = sc.nextInt();
        }
        setLine(line);
        
        System.out.println("Enter percentage of mine : ");
        percent = sc.nextInt();
        while(percent  > 85 || percent < 1) {
            System.out.println("Percentage of mine must be > 1 and < 85, please enter a new percentage of mine :");
            percent = sc.nextInt();
        }
        setPercent(percent);
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }
}