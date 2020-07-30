package MarsRovers;

import MarsRovers.domain.Coordinate;
import MarsRovers.domain.Direction;
import MarsRovers.domain.Position;
import MarsRovers.exception.ExplorationException;
import MarsRovers.rover.MarsRover;
import MarsRovers.rover.Rover;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class MarsRoverExploration {
    private Pattern coordinatesPattern = Pattern.compile("^(\\d)+ (\\d)+$");
    private Pattern roverPositionPattern = Pattern.compile("^(\\d)+ (\\d)+ [EWNS]$");
    private Pattern explorationCommandsPattern = Pattern.compile("^[LRM]*$");
    private Coordinate gridMinCoordinates = new Coordinate(0,0);
    private Coordinate gridMaxCoordinates;
    private int step = 1;

    public MarsRoverExploration(Coordinate gridMinCoordinates, Coordinate gridMaxCoordinates){
        this.gridMinCoordinates = gridMinCoordinates;
        this.gridMaxCoordinates = gridMaxCoordinates;
    }

    public MarsRoverExploration(){}

    public boolean explore(Rover rover, List<Character> operations) throws ExplorationException {
        if(gridMaxCoordinates == null) {
            throw new ExplorationException("Please set grid max coordinates to proceed");
        }
        for(Character action: operations){
            switch (action){
                case 'L':
                    rover.turnLeft();
                    break;
                case 'R':
                    rover.turnRight();
                    break;
                case 'M':
                    rover.move(gridMinCoordinates, gridMaxCoordinates, step);
                    break;
            }
        }
        return true;
    }

    public void explore(String fileName) throws ExplorationException {
        File file = getFile(fileName);
        if(file.length() == 0) {
            System.out.println("Nothing to process as file is empty.");
            return;
        }
        try(BufferedReader fileReader = new BufferedReader(new FileReader(file))){
            String gridCoordinatesString = fileReader.readLine();
            gridMaxCoordinates = getGridCoordinates(gridCoordinatesString);
            boolean process = true;

            while(process){
                String roverPositionString = fileReader.readLine();
                //Stop processing further if reached end of file
                if(roverPositionString == null) break;
                String explorationCommands = fileReader.readLine();

                MarsRover rover = getRover(roverPositionString);
                List<Character> commands = getExplorationCommands(explorationCommands);

                explore(rover, commands);
                System.out.println(getRoverPositionString(rover));
            }
        } catch (IOException exception){
            throw new ExplorationException("Failed to process file. Please validate input file. Exception Trace: " +
                    exception.getMessage());
        }
    }

    public boolean explore(Rover rover, String commandString) throws ExplorationException {
        if(gridMaxCoordinates == null) {
            throw new ExplorationException("Please set grid max coordinates to proceed");
        }
        List<Character> commands = getExplorationCommands(commandString);
        return explore(rover, commands);
    }

    private Coordinate getGridCoordinates(String  inputCoordinates) throws ExplorationException {
        validateStringForCoordinates(inputCoordinates);
        String[] inputs = inputCoordinates.split(" ");
        int x = Integer.valueOf(inputs[0]);
        int y = Integer.valueOf(inputs[0]);
        return new Coordinate(x,y);
    }
    private MarsRover getRover(String roverPositionString) throws ExplorationException {
        validateStringForRoverPosition(roverPositionString);
        String[] inputs = roverPositionString.split(" ");
        int x = Integer.valueOf(inputs[0]);
        int y = Integer.valueOf(inputs[1]);
        char direction = inputs[2].charAt(0);
        Position position =  new Position(new Coordinate(x,y), Direction.getDirection(direction));
        return new MarsRover(position);
    }

    private List<Character> getExplorationCommands(String explorationCommandsString) throws ExplorationException {
        validateStringForExplorationCommands(explorationCommandsString);
        List<Character> commands = new ArrayList<>();
        for(int index=0;index<explorationCommandsString.length();index++){
            commands.add(explorationCommandsString.charAt(index));
        }
        return commands;
    }

    private String getRoverPositionString(MarsRover rover) {
        Position position = rover.getPosition();
        return position.getCoordinate().getX() + " " + position.getCoordinate().getY() + " " + position.getDirection().toString().charAt(0);
    }

    private void validateStringForCoordinates(String inputCoordinates) throws ExplorationException {
        if(inputCoordinates == null || !coordinatesPattern.matcher(inputCoordinates).find()){
            throw  new ExplorationException("Unable to parse grid coordinates from '"+inputCoordinates+"'");
        }
    }

    private void validateStringForRoverPosition(String roverPositionString) throws ExplorationException {
        if(roverPositionString == null || !roverPositionPattern.matcher(roverPositionString).find()){
            throw  new ExplorationException("Unable to parse Rover position from '" + roverPositionString + "'");
        }
    }

    private void validateStringForExplorationCommands(String explorationCommandsString) throws ExplorationException {
        if(explorationCommandsString==null || !explorationCommandsPattern.matcher(explorationCommandsString).find()){
            throw new ExplorationException("Unable to parse exploration commands from '" + explorationCommandsString + "'");
        }
    }

    private File getFile(String fileName) throws ExplorationException {
        File file = new File(fileName);
        if(!file.exists()){
            throw new ExplorationException("Unable to find file. Please provide a valid file");
        }

        if(!file.canRead()){
            throw new ExplorationException("Unable to read file. Please grant read permissions to the program");
        }
        return file;
    }

    public void setGridMaxCoordinates(Coordinate gridMaxCoordinates) {
        this.gridMaxCoordinates = gridMaxCoordinates;
    }

    public void setGridMinCoordinates(Coordinate gridMinCoordinates) {
        this.gridMinCoordinates = gridMinCoordinates;
    }
}

