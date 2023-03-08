package service;

import dto.Owner;

import java.io.*;

public class SaveAndLoadService {

    public final String SAVEFILE_PATH = ".khplace.save"; // 숨김파일로 생성

    public boolean gameSave() {
        ObjectOutputStream oos = null;

        try {
            oos = new ObjectOutputStream(new FileOutputStream(SAVEFILE_PATH));
            oos.writeObject(Service.getOwner());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if( oos != null ) {
                try {
                    oos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return true;
    }

    private Owner readOwnerFromFile() {
        ObjectInputStream ois = null;
        Owner result = null;

        try {
            ois = new ObjectInputStream(new FileInputStream(SAVEFILE_PATH));
            result =  (Owner) ois.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if( ois != null ) {
                try {
                    ois.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return result;
    }

    public boolean gameLoad() {
        Owner owner = readOwnerFromFile();
        if( owner != null) Service.gameInitialization(owner);
        else return false;
        return true;
    }

    public boolean isSavedDataExists() {
        File saveFileIsExist = new File(SAVEFILE_PATH);
        return saveFileIsExist.exists();
    }

    public Owner getSavedData() {
        return readOwnerFromFile();
    }
}
