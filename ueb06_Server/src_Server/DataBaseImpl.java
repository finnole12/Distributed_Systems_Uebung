package src_Server;

import src_Shared.DBResult;
import src_Shared.DataBase;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Random;

public class DataBaseImpl implements DataBase {

    ArrayList<String> database = new ArrayList<>();

    public DataBaseImpl() {
        initilizeDatabase();
    }

    public static DataBaseImpl initialize() throws IOException {
        DataBaseImpl database = new DataBaseImpl();
        DataBase stub = (DataBase) UnicastRemoteObject.exportObject(database, 0);
        writeStubToFile("dbStub", stub);
        return database;
    }

    @Override
    public String getRecord(int index) throws RemoteException {
        try {
            return database.get(index);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    public void addRecord(int index, String record) throws RemoteException {
        database.add(index, record);
    }

    @Override
    public int getSize() throws RemoteException {
        return database.size();
    }

    @Override
    public int getIndex(String record) throws RemoteException {
        return database.indexOf(record);
    }

    @Override
    public DBResult getRecordObj(int index) throws RemoteException {
        DBResult dbResult = new DBResult();
        dbResult.setKey(index);
        dbResult.setValue(getRecord(index));
        return dbResult;
    }

    private static void writeStubToFile ( String fileName , Remote stub )
            throws FileNotFoundException, IOException {
        FileOutputStream fos = new FileOutputStream( fileName );
        ObjectOutputStream out = new ObjectOutputStream ( fos );
        out.writeObject( stub );
        out.close ();
    }

    private void initilizeDatabase() {
        for (int i = 0; i < 5000; i++) {
            database.add("Random Entry number: " + new Random().nextInt());
        }
    }
}
