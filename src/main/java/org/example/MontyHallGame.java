package org.example;

// Класс, представляющий конкретную игру по парадоксу Монти Холла
public class MontyHallGame extends Game {
    public MontyHallGame(int totalGames) {
        super(totalGames);
        if (totalGames <= 0) {
            throw new IllegalArgumentException("totalGames должно быть положительным числом");
        }
    }


    @Override
    public void play() {
        for (int i = 0; i < totalGames; i++) {
            // Создаем три двери и случайно выбираем, за какой из них будет автомобиль
            int carDoor = random.nextInt(3);

            // Игрок выбирает одну из дверей
            int playerChoice = random.nextInt(3);

            // Ведущий открывает одну из оставшихся дверей с козой
            int revealedDoor;
            do {
                revealedDoor = random.nextInt(3);
            } while (revealedDoor == carDoor || revealedDoor == playerChoice);

            // Решение игрока: остаться при своем выборе или сменить выбор
            int switchChoice = 3 - playerChoice - revealedDoor;

            // Проверяем, выиграл ли игрок, оставшись при своем выборе
            if (playerChoice == carDoor) {
                stayWins++;
            }

            // Проверяем, выиграл ли игрок, сменив выбор
            if (switchChoice == carDoor) {
                switchWins++;
            }

        }
    }
}
