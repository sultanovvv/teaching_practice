import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DiceGenerator {

    private int continuousRandomValue(int a, int b) {
        int res = 0;
        double r = Math.random();
        res = (int) (a + r * (b - a));
        return res;
    }

    private int[] diceExperiment(int countOfAttempts) {
        int diceResMas[] = new int[countOfAttempts];
        for (int i = 0; i < diceResMas.length; i++) {
            diceResMas[i] = continuousRandomValue(1, 6);
        }
        return diceResMas;
    }

    private double mathWaitEstimation(int[] mass) {
        double result = 0;
        int Xk = 0;
        for (int i = 0; i < mass.length; i++) {
            Xk += mass[i];
        }
        result = 1 / ((double) mass.length + 1) * Xk;
        return result;
    }

    public static void main(String[] args) {
        File file = new File("lab_1_res.txt");
        DiceGenerator diceGenerator = new DiceGenerator();

        try {
            FileWriter filewriter = new FileWriter(file);
            for (int i = 0; i < 10; i++) {
                int[] dicesMass = diceGenerator.diceExperiment(500);

                for (int j = 0; j < dicesMass.length; j++) {
                    filewriter.write(dicesMass[j] + " ");
                    if ((j + 1) % 25 == 0) filewriter.write("\n");
                    filewriter.flush();
                }
                filewriter.write("Оценка математического ожидания для эксперимента №" + (i + 1) + ": " + String.valueOf(diceGenerator.mathWaitEstimation(dicesMass)) + "\n");
                filewriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
