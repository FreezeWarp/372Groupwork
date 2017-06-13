import java.io.*;

/**
 * Created by joseph on 12/06/17.
 */
public class Theater implements Serializable {
    /* Singleton */
    private static Theater INSTANCE;

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
    /**
     * Reads the Theater object (and its static instance variable) from the ObjectOutputStream.
     *
     * @param input
     */
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


    /**
     * Writes the Theater object (and its static instance variable) to the ObjectOutputStream.
     *
     * @param output
     */
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
    /**
     * A file object corresponding to the persistence file, whether or whether not it exists.
     */
    private static final File persistenceFile = new File(new File(System.getProperty("user.dir")), "372Groupwork_Persistence.bin");


    /**
     * @return True if the persistence file exists, false otherwise.
     */
    public static boolean hasData() {
        return persistenceFile.exists();
    }


    /**
     * Writes Theater's state to the persistence file.
     */
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


    /**
     * Loads in data from the persistence file.
     */
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