import java.io.*;

/**
 * Created by joseph on 12/06/17.
 */
public class Theater implements Serializable {
    /* Singleton */
    private static Theater INSTANCE;
    private static final File persistenceFile = new File(new File(System.getProperty("user.dir")), "372Groupwork_Persistence.bin");

    private Theater() {
        clientList = ClientList.getInstance();
        customerList = CustomerList.getInstance();
    }

    public static Theater getInstance() {
        if (INSTANCE == null) {
             INSTANCE = new Theater();
        }

        return INSTANCE;
    }



    /* Singleton Serialisation */
    private void readObject(java.io.ObjectInputStream input) {
        try {
            input.defaultReadObject();

            if (INSTANCE == null) {
                INSTANCE = (Theater) input.readObject();
            }
            else {
                input.readObject();
            }
        } catch(IOException ioe) {
            ioe.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void writeObject(java.io.ObjectOutputStream output) {
        try {
            output.defaultWriteObject();
            output.writeObject(INSTANCE);
        } catch(IOException ioe) {
            ioe.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }



    /* Helper Methods to Write/Read to/from Disk */

    public static boolean hasData() {
        return persistenceFile.exists();
    }

    public static boolean storeData() {
        try {
            FileOutputStream out = new FileOutputStream(persistenceFile);
            ObjectOutputStream oos = new ObjectOutputStream(out);

            oos.writeObject(INSTANCE);
            return true;
            //oos.flush();
        } catch (Exception e) {
            System.out.println("Unable to serialise a value: " + e);
            return false;
        }
    }


    public static Theater retrieveData() {
        try {
            FileInputStream in = new FileInputStream(persistenceFile);
            ObjectInputStream ois = new ObjectInputStream(in);
            ois.readObject();
            return INSTANCE;
        } catch (IOException e) {
            System.out.println("Problem reading: " + e);
            return null;
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + e);
            return null;
        }
    }



    /* Theater Core */
    private ClientList clientList;
    private CustomerList customerList;
    public ClientList getClientList() {
        return clientList;
    }
    public CustomerList getCustomerList() {
        return customerList;
    }


}