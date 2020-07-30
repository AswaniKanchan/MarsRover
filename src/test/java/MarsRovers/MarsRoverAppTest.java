package test.java.MarsRovers;

import main.java.MarsRovers.MarsRoversApp;
import main.java.MarsRovers.exception.ExplorationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MarsRoverAppTest {
    @Test
    public void testMarsRoversAppForProvidedValidInputs() throws IOException, ExplorationException {
        String[] args = getArgsWithProvidedValidInputs();
        MarsRoversApp.main(args);
    }

    @Test
    public void testMarsRoversAppWithInvalidInputsWhereRoverTriesToCrossGrid() {
        Throwable exception = Assertions.assertThrows(ExplorationException.class,
                ()->{
                    String[] args = getArgsWithInvalidInputsWhereRoverTriesToCrossGrid();
                    MarsRoversApp.main(args); });
        assert(exception.getMessage().equals("Incorrect Move"));
    }

    @Test
    public void testMarsRoversAppWithNoRovers() throws IOException, ExplorationException {
        String[] args = getArgsWithNoRovers();
        MarsRoversApp.main(args);
    }

    @Test
    public void testMarsRoversAppWithInsufficientGridCoords() throws IOException {
        String[] args = getArgsWithInsufficientGridCoords();
        Throwable exception = Assertions.assertThrows(ExplorationException.class,
                ()->MarsRoversApp.main(args));
        assert(exception.getMessage().equals("Unable to parse grid coordinates from '5 '"));
    }

    @Test
    public void testMarsRoversAppWithMissingDirectionInRoverPosition() throws IOException {
        String[] args = getArgsWithMissingDirectionRoverPosition();

        Throwable exception = Assertions.assertThrows(ExplorationException.class,
                ()->MarsRoversApp.main(args));

        assert(exception.getMessage().equals("Unable to parse Rover position from '3 3 '"));

    }

    @Test
    public void testMarsRoversAppWithMissingCoordsInRoverPosition() throws IOException {
        String[] args = getArgsWithMissingCoordsInRoverPosition();

        Throwable exception = Assertions.assertThrows(ExplorationException.class,
                ()->MarsRoversApp.main(args));

        assert(exception.getMessage().equals("Unable to parse Rover position from '3 N '"));

    }

    @Test
    public void testMarsRoversAppWithIncorrectRoverDirection() {
        Throwable exception = Assertions.assertThrows(ExplorationException.class,
                ()->{
                    String[] args = getArgsWithIncorrectRoverDirection();
                    MarsRoversApp.main(args); });

        assert(exception.getMessage().equals("Unable to parse Rover position from '1 2 K'"));
    }

    @Test
    public void testMarsRoversAppWithInvalidExplorationCommands() {
        Throwable exception = Assertions.assertThrows(ExplorationException.class,
                ()->{
                    String[] args = getArgsWithIncorrectExplorationCommands();
                    MarsRoversApp.main(args);});

        assert(exception.getMessage().equals("Unable to parse exploration commands from 'ABC'"));
    }

    @Test
    public void testMarsRoversAppWithNullAsFileName() {
        Throwable exception = Assertions.assertThrows(ExplorationException.class,
                ()->{
                    String[] args = null;
                    MarsRoversApp.main(args);});

        assert(exception.getMessage().equals("Please provide input file to process for mars rover exploration"));
    }

    @Test
    public void testMarsRoversAppWithNoFileName() {
        Throwable exception = Assertions.assertThrows(ExplorationException.class,
                ()->{
                    String[] args = new String[0];
                    MarsRoversApp.main(args); });

        assert(exception.getMessage().equals("Please provide input file to process for mars rover exploration"));
    }

    @Test
    public void testMarsRoversAppWithRandomStringAsFileName() {
        Throwable exception = Assertions.assertThrows(ExplorationException.class,
                ()->{
                    String[] args = new String[1];
                    args[0]="RandomName";
                    MarsRoversApp.main(args); });

        assert(exception.getMessage().equals("Unable to find file. Please provide a valid file"));
    }

    @Test
    public void testMarsRoversAppWithEmptyFile() throws ExplorationException, IOException {
        String[] args = getArgsWithEmptyFile();
        MarsRoversApp.main(args);
    }

    @Test
    public void testMarsRoversAppWithFileThatCantBeRead() throws ExplorationException, IOException {
        String[] args = getArgsWithFileThatCantBeRead();
        Throwable exception = Assertions.assertThrows(ExplorationException.class,
                ()->MarsRoversApp.main(args));
        assert(exception.getMessage().equals("Unable to read file. Please grant read permissions to the program"));
    }

    private String[] getArgsWithIncorrectExplorationCommands() throws IOException {
        File file = new File("SampleInput.txt");
        file.createNewFile();
        file.deleteOnExit();
        FileWriter writer = new FileWriter(file.getName());
        writer.write("5 5\n1 2 N\nABC\n");
        writer.close();
        String[] args = new String[1];
        args[0]=file.getAbsolutePath();
        return args;
    }

    private String[] getArgsWithIncorrectRoverDirection() throws IOException {
        File file = new File("SampleInput.txt");
        file.createNewFile();
        file.deleteOnExit();
        FileWriter writer = new FileWriter(file.getName());
        writer.write("5 5\n1 2 K\nLMLMLMLMM\n");
        writer.close();
        String[] args = new String[1];
        args[0]=file.getAbsolutePath();
        return args;
    }

    private String[] getArgsWithFileThatCantBeRead() throws IOException {
        File file = new File("Empty.txt");
        file.createNewFile();
        file.deleteOnExit();
        file.setReadable(false);
        String[] args = new String[1];
        args[0]=file.getAbsolutePath();
        return args;
    }

    private String[] getArgsWithEmptyFile() throws IOException {
        File file = new File("Empty.txt");
        file.createNewFile();
        file.deleteOnExit();
        String[] args = new String[1];
        args[0]=file.getAbsolutePath();
        return args;
    }

    private String[] getArgsWithMissingDirectionRoverPosition() throws IOException {
        File file = new File("SampleInput.txt");
        file.createNewFile();
        file.deleteOnExit();
        FileWriter writer = new FileWriter(file.getName());
        writer.write("5 5\n1 2 N\nLMLMLMLMM\n3 3 \nMMRMMRMRRM");
        writer.close();
        String[] args = new String[1];
        args[0]=file.getAbsolutePath();
        return args;
    }

    private String[] getArgsWithMissingCoordsInRoverPosition() throws IOException {
        File file = new File("SampleInput.txt");
        file.createNewFile();
        file.deleteOnExit();
        FileWriter writer = new FileWriter(file.getName());
        writer.write("5 5\n1 2 N\nLMLMLMLMM\n3 N \nMMRMMRMRRM");
        writer.close();
        String[] args = new String[1];
        args[0]=file.getAbsolutePath();
        return args;
    }

    private String[] getArgsWithProvidedValidInputs() throws IOException {
        File file = new File("SampleInput.txt");
        file.createNewFile();
        file.deleteOnExit();
        FileWriter writer = new FileWriter(file.getName());
        writer.write("5 5\n1 2 N\nLMLMLMLMM\n3 3 E\nMMRMMRMRRM");
        writer.close();
        String[] args = new String[1];
        args[0]=file.getAbsolutePath();
        return args;
    }

    private String[] getArgsWithInvalidInputsWhereRoverTriesToCrossGrid() throws IOException {
        File file = new File("SampleInput.txt");
        file.createNewFile();
        file.deleteOnExit();
        FileWriter writer = new FileWriter(file.getName());
        writer.write("5 5\n5 5 N\nM");
        writer.close();
        String[] args = new String[1];
        args[0]=file.getAbsolutePath();
        return args;
    }

    private String[] getArgsWithNoRovers() throws IOException {
        File file = new File("SampleInput.txt");
        file.createNewFile();
        file.deleteOnExit();
        FileWriter writer = new FileWriter(file.getName());
        writer.write("5 5");
        writer.close();
        String[] args = new String[1];
        args[0]=file.getAbsolutePath();
        return args;
    }

    private String[] getArgsWithInsufficientGridCoords() throws IOException {
        File file = new File("SampleInput.txt");
        file.createNewFile();
        file.deleteOnExit();
        FileWriter writer = new FileWriter(file.getName());
        writer.write("5 \n5 5 N\nM");
        writer.close();
        String[] args = new String[1];
        args[0]=file.getAbsolutePath();
        return args;
    }
}
