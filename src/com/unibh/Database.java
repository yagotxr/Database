package com.unibh;

import com.unibh.logger.Logger;

import static java.util.Objects.isNull;

public class Database {

    private final Logger logger = new Logger("Database");
    private Alumn[] backup;

    private Alumn[] primary;
    private int registries;

    public Database(int size) {
        this.primary = new Alumn[size];
        this.backup = primary;
    }

    public Alumn insert(Alumn alumn) {
        for (int i = 0; i < primary.length; i++) {
            if (isNull(primary[i])) {
                primary[i] = alumn;
                backup[i] = alumn;

                logger.info("Inserted: " + alumn.toString());
                registries++;
                return alumn;
            }
        }

        throw new NotAcceptedException("Array is full.");
    }

    public Alumn find(long ra){
        int iterations = 0;
        logger.info("Non-ordered array");
        logger.info("Searching alumn: RA " + ra);
        for (Alumn a : primary) {
            if (a.getRa() == ra) {
                logger.info("Comparations made: " + iterations + " times.");
                return a;
            }
            iterations++;
        }
        return null;
    }

    public static void orderDatabase(Database database) {
        Alumn[] array = database.primary;
        int registries = database.getRegistries();

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
    }

    public int getRegistriesQuantity() {
        int result = 0;
        for (Alumn a : primary) {
            if (isNull(a))
                break;
            result++;
        }
        return result;
    }

    public void print() {
        for (Alumn a : primary) {
            if (a != null)
                System.out.println(a.toString());
        }
    }

    public void reset() {
        this.primary = backup;
    }

    public int getRegistries() {
        return registries;
    }
}
