import java.io.*;

/**
 * Created by joseph on 12/06/17.
 */
public class Theater implements Serializable {
	 /* Singleton Stuff */
	/**
     * The singleton instance.
     */
    private static Theater INSTANCE;

    private Theater() {}

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
     * @param input The stream being read from
     * 
     * @return nothing
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
     * @param output The stream being written to
     * 
     * @return nothing
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
     * Determines if the persistence file exists with data
     * 
     * @param nothing
     * 
     * @return True if the persistence file exists, false otherwise.
     */
    public static boolean hasData() {
        return persistenceFile.exists();
    }


    /**
     * Writes Theater's state to the persistence file.
     *
     * @param nothing
     *
     * @return True on success, false on failure.
     */
    public static boolean storeData() {
        try {
            FileOutputStream out = new FileOutputStream(persistenceFile);
            ObjectOutputStream oos = new ObjectOutputStream(out);

            oos.writeObject(INSTANCE);
            return true;
            //oos.flush();
        } catch (Exception e) {
            System.err.println("Theater.storeData: Unable to serialise a value: " + e);
            return false;
        }
    }


    /**
     * Loads in data from the persistence file.
     * 
     * @param nothing
     *
     * @return The Theater instance on success, or null on failure.
     */
    public static Theater retrieveData() {
        try {
            FileInputStream in = new FileInputStream(persistenceFile);
            ObjectInputStream ois = new ObjectInputStream(in);
            ois.readObject();
            return INSTANCE;
        } catch (IOException e) {
            System.err.println("Theater.retrieveData: Problem reading: " + e);
            return null;
        } catch (ClassNotFoundException e) {
            System.err.println("Theater.retrieveData: Class not found: " + e);
            return null;
        }
    }



    /* Theater Core */
    private ClientList clientList = ClientList.getInstance();
    private CustomerList customerList = CustomerList.getInstance();
    private ShowList showList = ShowList.getInstance();

    public static ClientList getClientList() {
        return getInstance().clientList;
    }
    
    public static CustomerList getCustomerList() {
        return getInstance().customerList;
    }
    public static ShowList getShowList() {
        return getInstance().showList;
    }


}