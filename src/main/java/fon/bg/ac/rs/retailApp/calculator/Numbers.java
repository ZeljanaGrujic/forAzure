package fon.bg.ac.rs.retailApp.calculator;

public class Numbers {

    public int treeDigitsum(int numb){

        int x, y,z;
        if(numb>=1000){
            throw new RuntimeException("Invalid Number!");
        }
        x=numb/100;
        y=(numb/10)%10;
        z=numb%10;

        return x+y+z;
    }
}
