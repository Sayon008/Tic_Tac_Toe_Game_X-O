package TicTacToeNew.factories;


import TicTacToeNew.models.BotDifficultyLevel;
import TicTacToeNew.strategies.botplayingstrategy.BotPlayingStrategy;
import TicTacToeNew.strategies.botplayingstrategy.EasyBotPlayingStrategy;
import TicTacToeNew.strategies.botplayingstrategy.HardBotPlayingStrategy;
import TicTacToeNew.strategies.botplayingstrategy.MediumBotPlayingStrategy;

public class BotPlayingStrategyFactory {

//    For this method we have a input parameter of BotDifficultyLevel
    public static BotPlayingStrategy getBotPlayingStrategy(BotDifficultyLevel botDifficultyLevel) {
        if(botDifficultyLevel.equals(BotDifficultyLevel.EASY)){
            return new EasyBotPlayingStrategy();
        }
        else if(botDifficultyLevel.equals(BotDifficultyLevel.MEDIUM)){
            return new MediumBotPlayingStrategy();
        }
        else{
            return new HardBotPlayingStrategy();
        }
    }
}
