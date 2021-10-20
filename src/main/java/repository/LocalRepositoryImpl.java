package repository;

import constants.Exceptions;
import driver.IShellState;
import driver.JShellState;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;


/**
 * The Local Repository has access to the computer's local file system
 */

public class LocalRepositoryImpl implements DataRepositoryInterface {

    public LocalRepositoryImpl(){
        super();
    }


    @Override
    public void writeJShell(IShellState serializeObj, String path) throws Exception {
        try{
            FileOutputStream fileOut = new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(serializeObj);
            out.close();
            fileOut.close();
        } catch (FileNotFoundException e) {
            throw new Exception(Exceptions.WRONG_PATH_INPUT_MSG);
        } catch (IOException e) {
            throw new Exception(Exceptions.WRONG_PATH_INPUT_MSG);
        }

    }

    @Override
    public void loadJShell(IShellState currentShell, String path) throws Exception {
        JShellState shellState = null;
        try{
            FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            shellState = (JShellState) in.readObject();
            in.close();
            fileIn.close();
            currentShell.loadExistedJShellState(shellState);
        } catch (FileNotFoundException e) {
            throw new Exception(Exceptions.WRONG_PATH_INPUT_MSG);
        } catch (IOException e) {
            throw new Exception(Exceptions.WRONG_PATH_INPUT_MSG);
        }
    }
}
