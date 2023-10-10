package org.example;

import java.util.Random;

// Абстрактный класс, представляющий игру
public abstract class Game {
    public int totalGames;
    public int stayWins;
    public int switchWins;
    protected Random random;

    public Game(int totalGames) {
        this.totalGames = totalGames;
        this.stayWins = 0;
        this.switchWins = 0;
        this.random = new Random();
    }

    // Абстрактный метод, который реализуется в подклассах
    public abstract void play();
}
