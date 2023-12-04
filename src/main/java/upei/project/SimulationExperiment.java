package upei.project;
import upei.project.Properties.Country;
import upei.project.Properties.Station;
import upei.project.Properties.Utility;
import upei.project.Setup.BoardInit;
import upei.project.Setup.PlayersInit;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * The SimulationExperiment class conducts a simulation experiment for a Monopoly game.
 * It runs multiple trials and records results to analyze player strategies.
 */
public class SimulationExperiment {

    public static int numOfInf = 0;
    /**
     * Main method to execute the Monopoly game simulation experiment.
     * @param args Command-line arguments (not used in this implementation).
     */
    public static void main(String[] args) {
        // Exporting the dataset to a CSV file
        //hashMapToCSV(dataset, "./data_unused/stationGuyVsUtilityGuy.csv");
        final int TRIALS = 60;
        simGreedyStingy(TRIALS, false);
        simStationUtility(TRIALS, false);
        simAllStrategies(TRIALS, false);
    }
    /**
     * Calculates the win rate per strategy based on the dataset.
     * @param dataset The HashMap containing trial results with players and their strategies.
     * @return HashMap containing the win rate per strategy.
     */
    private static HashMap<String, Integer> calcWinRatePerStrategy(HashMap<Integer, Player> dataset){
        HashMap<String, Integer> winsPerStrategy = new HashMap<>();
        String tempStrategy;
        for(int i=0; i< dataset.size(); i++){
            if(dataset.get(i) != null) {
                tempStrategy = dataset.get(i).getStratey().toString();

                if (winsPerStrategy.containsKey(tempStrategy)) {
                    winsPerStrategy.put(tempStrategy, winsPerStrategy.get(tempStrategy) + 1);
                } else {
                    winsPerStrategy.put(tempStrategy, 1);
                }
            }
        }
        return winsPerStrategy;
    }
    /**
     * Writes the dataset to a CSV file.
     * @param hashMap   The HashMap containing trial results with players and their strategies.
     * @param fileName  The name of the CSV file to write the data.
     */
    private static void hashMapToCSV(HashMap<Integer, Player> hashMap, String fileName){
        try(FileWriter writer = new FileWriter(fileName)){
            writer.write("strategy,money,countries,utilities,stations,totalLandsOwned\n");
            for(int i=0; i<hashMap.size(); i++){
                Player p = hashMap.get(i);
                if(p != null) {
                    String strategy = p.getStratey().toString();
                    int money = p.getMoney();
                    int countries = p.getLandsOwnedOfType(Country.class).size();
                    int utilities = p.getLandsOwnedOfType(Utility.class).size();
                    int stations = p.getLandsOwnedOfType(Station.class).size();
                    int landsOwned = p.getLandsOwned().size();
                    writer.write(strategy +","+ money +","+ countries +","+ utilities +","+ stations +","+ landsOwned + "\n");
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void simGreedyStingy(int numTrials, boolean display){
        System.out.print("SIMULATION OF : GREEDY VS STINGY\n");
        System.out.print("==========================================\n");
        HashMap<Integer, Player> dataset = new HashMap<>();
        ArrayList<Player> players;
        ArrayList<BoardSquare> boardMap;
        MonopolyGame game = null;
        int totalInf = 0;
        // Running multiple trials of the Monopoly game
        for(int i=0; i<numTrials; i++) {
            players = PlayersInit.getPlayers(new String[]{"Zeyad", "Hashem"},
                    new Player.strategy[]{Player.strategy.GREEDY, Player.strategy.STINGY});
            boardMap = BoardInit.createBoard();
            game = new MonopolyGame(players, boardMap);
            dataset.put(i, game.playGame(display));
            totalInf += game.getNumOfInf();
        }
        System.out.println("Winners for this simulation per strategy:\n" + calcWinRatePerStrategy(dataset));
        System.out.println("Num non-ending games in this simulation: " + totalInf+"\n");
        hashMapToCSV(dataset, "./data_unused/GreedyStingy.csv");

    }
    private static void simStationUtility(int numTrials, boolean display){
        System.out.print("SIMULATION OF : STATION_GUY VS UTILITY_GUY\n");
        System.out.print("==========================================\n");
        HashMap<Integer, Player> dataset = new HashMap<>();
        ArrayList<Player> players;
        ArrayList<BoardSquare> boardMap;
        MonopolyGame game = null;
        int totalInf = 0;
        // Running multiple trials of the Monopoly game
        for(int i=0; i<numTrials; i++) {
            players = PlayersInit.getPlayers(new String[]{"Zeyad", "Hashem"},
                    new Player.strategy[]{Player.strategy.STATION_GUY, Player.strategy.UTILITY_GUY});
            boardMap = BoardInit.createBoard();
            game = new MonopolyGame(players, boardMap);
            dataset.put(i, game.playGame(display));
            totalInf += game.getNumOfInf();
        }
        System.out.println("Winners for this simulation per strategy:\n" + calcWinRatePerStrategy(dataset));
        System.out.println("Num non-ending games in this simulation: " + totalInf+"\n");
        hashMapToCSV(dataset, "./data_unused/StationUtility.csv");

    }
    private static void simAllStrategies(int numTrials, boolean display){
        System.out.print("SIMULATION OF : ALL STRATEGIES\n");
        System.out.print("==========================================\n");
        HashMap<Integer, Player> dataset = new HashMap<>();
        ArrayList<Player> players;
        ArrayList<BoardSquare> boardMap;
        MonopolyGame game = null;
        int totalInf = 0;
        // Running multiple trials of the Monopoly game
        for(int i=0; i<numTrials; i++) {
            players = PlayersInit.getPlayers(new String[]{"Zeyad", "Hashem", "Yasser", "Nadeem", "RJ"},
                    new Player.strategy[]{Player.strategy.GREEDY, Player.strategy.STINGY, Player.strategy.STATION_GUY,
                            Player.strategy.UTILITY_GUY, Player.strategy.DEFAULT});
            boardMap = BoardInit.createBoard();
            game = new MonopolyGame(players, boardMap);
            dataset.put(i, game.playGame(display));
            totalInf += game.getNumOfInf();
        }
        System.out.println("Winners for this simulation per strategy:\n" + calcWinRatePerStrategy(dataset));
        System.out.println("Num non-ending games in this simulation: " + totalInf+"\n");
        hashMapToCSV(dataset, "./data_unused/AllStrategies.csv");
    }


}

/*for(int i=0; i< dataset.size(); i++){
            System.out.print(i + " " + dataset.get(i).getStratey().toString() + "\n");
        }*/