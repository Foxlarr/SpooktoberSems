package org.example;


public class Main {
    public static void main(String[] args) {
        int totalGames = 1000;

        Game game = new MontyHallGame(totalGames);
        game.play();

        System.out.println("Игры, в которых игрок остался при своем выборе и выиграл: " + game.stayWins);
        System.out.println("Игры, в которых игрок сменил выбор и выиграл: " + game.switchWins);
    }
}
