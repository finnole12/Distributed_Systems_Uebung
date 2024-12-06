package src_Client;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.rmi.RemoteException;

public class RMI_Client {

    DataBase db;

    public RMI_Client() throws IOException, ClassNotFoundException {
        db = readStubFromFile("dbStub");
    }

    private static DataBase readStubFromFile ( String fileName )
            throws FileNotFoundException, IOException, ClassNotFoundException {
        /* Deserialize stub using Java Serialization */
        FileInputStream fis = new FileInputStream( fileName );
        ObjectInputStream in = new ObjectInputStream( fis );
        DataBase remoteObj = (DataBase) in . readObject ();
        in . close ();
        return remoteObj ;
    }

    public void execute() throws RemoteException {
        System.out.println("CLIENT: executing");
        System.out.println("CLIENT: adding records");
        db.addRecord(4101, "Appen");
        db.addRecord(4102, "Ahrensburg");
        db.addRecord(4103, "Wedel");
        db.addRecord(4104, "Aumühle");
        db.addRecord(4105, "Seevetal");
        db.addRecord(4106, "Quickborn");
        System.out.println("CLIENT: reading: " + db.getRecord(4103));
        System.out.println("CLIENT: reading: " + db.getRecord(4107));
        System.out.println("CLIENT: checking Size: " + db.getSize());
    }
}
