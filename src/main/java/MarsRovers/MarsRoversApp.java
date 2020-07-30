/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package MarsRovers;

import MarsRovers.exception.ExplorationException;

public class MarsRoversApp {

    public static void main(String[] args) throws ExplorationException {
        MarsRoverExploration marsRoverExploration = new MarsRoverExploration();
        if(args != null && args.length>=1){
            marsRoverExploration.explore(args[0]);
        } else{
            throw new ExplorationException("Please provide input file to process for mars rover exploration");
        }
    }
}
