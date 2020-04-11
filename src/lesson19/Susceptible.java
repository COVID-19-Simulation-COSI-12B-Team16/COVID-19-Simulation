package lesson19;

public class Susceptible extends StayAtHomeIfSick {
    Susceptible(){
        super();
        this.infectionProb = 0.8;
        this.recoveryTime = 10;
    }
}
