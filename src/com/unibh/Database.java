package com.unibh;

import com.unibh.logger.Logger;

import static java.lang.System.arraycopy;
import static java.util.Objects.isNull;

public class Database {

    private long operations;
    private final Logger logger = new Logger();
    private Alumn[] backup;

    private Alumn[] primary;
    private int registries;

    public Database(int size) {
        this.primary = new Alumn[size];
        this.backup = new Alumn[size];
        this.operations = 0;
    }

    public Alumn insert(Alumn alumn) {
        if (isFull()) {
            operations++;
            logger.info("Database is full.");
        } else {
            for (int i = 0; i < primary.length; i++) { //O(n)
                if (isNull(primary[i])) {
                    operations++;
                    primary[i] = alumn;
                    backup[i] = alumn;
                    logger.info("Inserted: " + alumn.toString());
                    registries++;
                    return alumn;
                }
            }
        }
        return null;
    }

    public void remove(Alumn alumn) {
        if (isEmpty()) {
            operations++;
            logger.info("Database is empty.");
        } else {
            for (int i = 0; i < registries; i++) { //O(n)
                if (primary[i].equals(alumn)) {
                    operations++;
                    primary[i] = null;
                    rearrange(primary, backup);
                    logger.info("Alumn removed from database.");
                    registries--;
                    break;
                }
            }
        }
    }

    public Alumn find(long ra) {
        logger.info("RA search - " + ra);
        for (int i = 0; i < registries; i++) { //O(n)
            if (primary[i].getRa() == ra) {
                operations++;
                logger.info(primary[i].toString());
                logger.info("Comparations made: " + (i + 1) + " times.");
                return primary[i];
            }
        }
        return null;
    }

    public Alumn find(String name) {
        logger.info("Fullname search - " + name.toUpperCase());
        for (int i = 0; i < registries; i++) { //O(n)
            if (primary[i].getName().equalsIgnoreCase(name)) {
                operations++;
                logger.info(primary[i].toString());
                logger.info("Comparations made: " + (i + 1) + " times.");
                return primary[i];
            }
        }
        return null;
    }

    public void order() {
        Alumn[] array = primary;

        int min;
        Alumn temp;

        for (int i = 0; i < registries - 1; i++) { //O(nˆ2)
            min = i;
            for (int j = i + 1; j < registries; j++) {
                if (array[j].getRa() < array[min].getRa()) {
                    operations++;
                    min = j;
                }
            }

            temp = array[min];
            array[min] = array[i];
            array[i] = temp;
        }
        logger.info("Database successfully ordered.");
    }

    public void print() {
        for (Alumn a : primary) { //O(n)
            if (a != null){
                operations++;
                System.out.println(a.toString());
            }

        }
    }

    public void reset() {
        if (registries >= 0){
            operations++;
            arraycopy(backup, 0, primary, 0, registries);
        }
        logger.info("Database successfully recovered.");
    }

    private void rearrange(Alumn[]... arrays) {
        for (Alumn[] array : arrays) { //O(nˆ2)
            for (int i = 0; i < registries; i++) {
                if (isNull(array[i])) {
                    operations++;
                    int j = i + 1;
                    while (j < registries) {
                        array[j - 1] = array[j];
                        j++;
                    }
                    array[j - 1] = null;
                }
            }
        }
    }

    public Alumn[] getPrimary() {
        return primary;
    }

    public boolean isFull() {
        int last = primary.length - 1;
        return !isNull(primary[last]);
    }

    public boolean isEmpty() {
        return isNull(primary[0]);
    }

    public long getOperations() {
        return operations;
    }
}
