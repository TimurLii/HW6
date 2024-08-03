package org.example;


import java.util.*;

public class Game {
    static Random random = new Random();

    public static void main(String[] args) {

        System.out.println("Соотношение выйгрышей к проигрышам без изменения решения.\n");
        List<Boolean> threeDoors = new ArrayList<>(Arrays.asList(false, true, false));
        Map<Integer, String> tableWinAndLoseNotChanges = new HashMap<>();
        startGameNotChange(threeDoors, tableWinAndLoseNotChanges);
        printResult(countWin(tableWinAndLoseNotChanges));

        System.out.println("Cоотношение выйигрышей к проигрышам с изменением решения.\n");
        Map<Integer, String> tableWinAndLoseWithChanges = new HashMap<>();
        startGameWithChange(threeDoors, tableWinAndLoseWithChanges);
        printResult(countWin(tableWinAndLoseWithChanges));


    }

    private static void startGameNotChange(List<Boolean> threeDoors, Map<Integer, String> tableWinAndLose) {
        for (int i = 1; i <= 100; i++) {
            int currentDoors = random.nextInt(0, 3);
            Collections.shuffle(threeDoors);
            if (threeDoors.get(currentDoors)) {
                tableWinAndLose.put(i, "Win");

            } else {
                tableWinAndLose.put(i, "Lose");
            }
        }

    }

    private static void startGameWithChange(List<Boolean> threeDoors, Map<Integer, String> tableWinAndLose) {
        for (int i = 1; i <= 100; i++) {
            int currentDoors = random.nextInt(0, 3);
            Collections.shuffle(threeDoors);
            int openDoor = getNewChoice(currentDoors);
            while (threeDoors.get(openDoor)) {
                openDoor = getNewChoice(currentDoors, openDoor);
            }
            int newCurrentDoors = getNewChoice(currentDoors, openDoor);
            if (threeDoors.get(newCurrentDoors)) {
                tableWinAndLose.put(i, "Win");
            } else {
                tableWinAndLose.put(i, "Lose");
            }

        }
    }

    private static int countWin(Map<Integer, String> map) {
        int count = 0;
        String value;
        for (var el : map.entrySet()) {
            value = el.getValue();
            if (value.equals("Win")) {
                count++;
            }
        }
        return count;

    }

    public static void printResult(int count) {
        String[] categories = {"Win", "Lose"};
        int[] values = {count, (100 - count)};


        for (int i = 0; i < categories.length; i++) {
            System.out.printf("%-10s | ", categories[i]);
            int barLength = (int) ((double) values[i] / 100 * 50);

            for (int j = 0; j < barLength; j++) {
                System.out.print("=");
            }
            System.out.printf("%d%n ", values[i]);
        }

    }

    public static int getNewChoice(int choiceDoor, int openDoor) {
        int newCurrentDoor = random.nextInt(0, 3);
        while ((newCurrentDoor == choiceDoor) || (newCurrentDoor == openDoor)) {
         newCurrentDoor = random.nextInt(0, 3);
        }
        return newCurrentDoor;
    }

    public static int getNewChoice(int choiceDoor) {
        int newCurrentDoor = random.nextInt(0, 3);
        while ((newCurrentDoor == choiceDoor)) {
             newCurrentDoor = random.nextInt(0, 3);
        }
        return newCurrentDoor;
    }


}
