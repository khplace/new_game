package service;

import dto.Owner;
import dto.SaveData;

import java.io.*;

public class SaveAndLoadService {

    public final String SAVEFILE_PATH = ".khplace.save"; // 숨김파일로 생성

    public boolean gameSave() {
        ObjectOutputStream oos = null;

        try {
            oos = new ObjectOutputStream(new FileOutputStream(SAVEFILE_PATH));
            oos.writeObject(new SaveData(Service.getOwner()));
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

    public SaveData getSavedData() {
        ObjectInputStream ois = null;
        SaveData saveData = null;

        try {
            ois = new ObjectInputStream(new FileInputStream(SAVEFILE_PATH));
            saveData = (SaveData) ois.readObject();
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

        return saveData;
    }

    public boolean gameLoad() {
        Owner owner = getSavedData().getOwner();
        if( owner != null) Service.gameInitialization(owner);
        else return false;
        return true;
    }

    public boolean isSavedDataExists() {
        File saveFileIsExist = new File(SAVEFILE_PATH);
        return saveFileIsExist.exists();
    }
}
