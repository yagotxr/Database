package com.unibh;

import com.unibh.logger.Logger;

import static java.lang.System.arraycopy;
import static java.util.Objects.isNull;

public class Database {

    private final Logger logger = new Logger();
    private Alumn[] backup;

    private Alumn[] primary;
    private int registries;

    public Database(int size) {
        this.primary = new Alumn[size];
        this.backup = new Alumn[size];
    }

    public Alumn insert(Alumn alumn) {
        if (isFull()) {
            logger.info("Database is full.");
        } else {
            for (int i = 0; i < primary.length; i++) {
                if (isNull(primary[i])) {
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
            logger.info("Database is empty.");
        } else {
            for (int i = 0; i < registries; i++) {
                if (primary[i].equals(alumn)) {
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
        for (int i = 0; i < registries; i++) {
            if (primary[i].getRa() == ra) {
                logger.info(primary[i].toString());
                logger.info("Comparations made: " + (i + 1) + " times.");
                return primary[i];
            }
        }
        return null;
    }

    public Alumn find(String name) {
        logger.info("Fullname search - " + name.toUpperCase());
        for (int i = 0; i < registries; i++) {
            if (primary[i].getName().equalsIgnoreCase(name)) {
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

        for (int i = 0; i < registries - 1; i++) {
            min = i;
            for (int j = i + 1; j < registries; j++) {
                if (array[j].getRa() < array[min].getRa()) {
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
        for (Alumn a : primary) {
            if (a != null)
                System.out.println(a.toString());
        }
    }

    public void reset() {
        if (registries >= 0)
            arraycopy(backup, 0, primary, 0, registries);
        logger.info("Database successfully recovered.");
    }

    private void rearrange(Alumn[]... arrays) {
        for (Alumn[] array : arrays) {
            for (int i = 0; i < registries; i++) {
                if (isNull(array[i])) {
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
}
