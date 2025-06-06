package TicTacToeNew.models;

import TicTacToeNew.factories.BotPlayingStrategyFactory;
import TicTacToeNew.strategies.botplayingstrategy.BotPlayingStrategy;

public class Bot extends Player{
    private BotDifficultyLevel botDifficultyLevel;

    private BotPlayingStrategy botPlayingStrategy;

//    We don't need this parameter in the constructor of the bot as an input- BotPlayingStrategy botPlayingStrategy
//    As we already have the BotDifficultyLevel present in the paramter list
//    Using the BotDifficultyLevel we can define or have the BotPlayingStrategy object

//    How can we define the BotPlayingStrategy now?
//    Definition - If we want to create some object of some class based on an input parameter(BotDifficultyLevel)  ---> We use Factory Pattern
//    Create a BotPlayingStrategyFactory class
    public Bot(Symbol symbol, String name, PlayerType playerType, BotDifficultyLevel botDifficultyLevel) {
        super(symbol, name, playerType);
        this.botDifficultyLevel = botDifficultyLevel;
        this.botPlayingStrategy = BotPlayingStrategyFactory.getBotPlayingStrategy(botDifficultyLevel);
    }

    public BotPlayingStrategy getBotPlayingStrategy() {
        return botPlayingStrategy;
    }

    public void setBotPlayingStrategy(BotPlayingStrategy botPlayingStrategy) {
        this.botPlayingStrategy = botPlayingStrategy;
    }

    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }

    public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        this.botDifficultyLevel = botDifficultyLevel;
    }

    @Override
    public Move makeMove(Board board) {
        // Now how can we implement the logic for makeMove for Bot
        return super.makeMove(board);
    }
}
