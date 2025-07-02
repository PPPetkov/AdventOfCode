import week4.day21.FractalArt;
import week4.day22.SporificaVirus;
import week4.day23.CoprocessorConflagration;
import week4.day24.ElectromagneticMoat;
import week4.day25.TheHaltingProblem;

public class Main {
    public static void main(String[] args) {
        String filename = "resources/input.txt";

        FractalArt solution = new FractalArt();
        System.out.println(solution.partOne(filename));
        System.out.println(solution.partTwo(filename));
    }
}
