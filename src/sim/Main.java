package sim;

import java.util.Arrays;

import control.*;

class Main {
    public static void main(String[] args) {

        Boolean verbose = false;

        if (args.length >0 && args[0] == "verbose"){
            verbose = true;
        }
        int biteFactor = 10,
        maxCurrentStrength = 20,
        maxDensity = 3,
        n = 4,
        predatorCloneTime = 10,
        preyCloneTime = 2;
        Fakarava.init(biteFactor, maxCurrentStrength, maxDensity, n, predatorCloneTime,
        preyCloneTime, null);
        //Long.parseLong("1238577643")

        int jojo = Fakarava.createPrey("Mérou", 3.0, 1, 2, 99);
        int lola = Fakarava.createPrey("Poisson Lune", 1.0, 0, 1, 90);
        int alfred = Fakarava.createPrey("Poisson Perroquet", 2.0, 0, 3, 51);
        int bertha = Fakarava.createPredator("Requin Marteau", 100.0, 1, 2);
        int adolphe = Fakarava.createPredator("Requin Gris", 75.0, 0, 2);
        int p1 = Fakarava.createFishway(0, 2);
        int enzo = Fakarava.createDiver("Enzo", "Tahiti");
        Fakarava.clockForward();
        Fakarava.putCamera(enzo, p1);
        Fakarava.putTransmitters(enzo, p1);
        long time = 1;
        
        while(! Fakarava.end){
            Fakarava.clockForward();
            time++;
            if (verbose) {
                System.out.println(Arrays.toString(Fakarava.spyReport())+"\n\n");
            }
        }
        System.out.println(time);
    }
}