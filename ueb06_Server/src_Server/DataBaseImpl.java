package src_Server;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class DataBaseImpl implements DataBase {

    ArrayList<String> database = new ArrayList<>();

    public static DataBaseImpl initialize() throws IOException {
        DataBaseImpl database = new DataBaseImpl();
        UnicastRemoteObject.exportObject(database);
        writeStubToFile("dbStub", database);
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

    private static void writeStubToFile ( String fileName , Remote stub )
            throws FileNotFoundException, IOException {
        FileOutputStream fos = new FileOutputStream( fileName );
        ObjectOutputStream out = new ObjectOutputStream ( fos );
        out.writeObject( stub );
        out.close ();
    }
}
