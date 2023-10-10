import org.example.MontyHallGame;
import org.example.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MontyHallGameTestSecond {
    private Game game;

    @BeforeEach
    public void setUp() {
        int totalGames = 10000; // Specify the number of games
        game = new MontyHallGame(totalGames);
    }

    @ParameterizedTest
    @ValueSource(ints = {100, 1000, 10000}) // Разные значения totalGames
    public void testPlayWithDifferentTotalGames(int totalGames) {
        game = new MontyHallGame(totalGames);
        game.play();

        double stayWinPercentage = (double) game.stayWins / totalGames * 100;
        double switchWinPercentage = (double) game.switchWins / totalGames * 100;

        // Проверяем, что процент выигрышей при сохранении своего выбора близок к 33%
        assertTrue(Math.abs(stayWinPercentage - 33.33) < 2.0);

        // Проверяем, что процент выигрышей при смене выбора близок к 66%
        assertTrue(Math.abs(switchWinPercentage - 66.67) < 2.0);
        System.out.println("stayWinPercentage: " + stayWinPercentage);
        System.out.println("switchWinPercentage: " + switchWinPercentage);

    }
}
