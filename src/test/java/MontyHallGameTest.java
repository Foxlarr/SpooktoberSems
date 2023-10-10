import org.example.MontyHallGame;

import org.example.Game;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class MontyHallGameTest {
    private Game game;

    @Before
    public void setUp() {
        int totalGames = 1000;
        game = new MontyHallGame(totalGames);
    }

    @Test
    public void testPlay() {
        game.play();
        int totalWins = game.stayWins + game.switchWins;

        // Проверяем, что сумма выигрышей равна общему количеству игр
        assertEquals(game.totalGames, totalWins);

        // Проверяем, что выигрыши остаются и сменяются, и их сумма равна общему количеству игр
        assertNotEquals(game.stayWins, 0);
        assertNotEquals(game.switchWins, 0);
        assertEquals(totalWins, game.stayWins + game.switchWins);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeInput() {
        // Проверяем, что исключение IllegalArgumentException выбрасывается при некорректном вводе
        Game negativeGame = new MontyHallGame(-100);
    }
}
