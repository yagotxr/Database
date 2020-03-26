package com.unibh;

import com.unibh.logger.Logger;

import static java.util.Objects.isNull;

public class Database {

    private final Logger logger = new Logger("Database");
    private Alumn[] array;
    private int registries;

    public Database(int size) {
        this.array = new Alumn[size];
    }

    public Alumn insert(Alumn alumn) {
        for (int i = 0; i < array.length; i++) {
            if (isNull(array[i])) {
                array[i] = alumn;
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
        for (Alumn a : array) {
            if (a.getRa() == ra) {
                logger.info("Comparations made: " + iterations + " times.");
                return a;
            }
            iterations++;
        }
        return null;
    }

    public static void orderDatabase(Database database) {
        Alumn[] array = database.getAlumns();
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
        for (Alumn a :
                array) {
            if (isNull(a))
                break;
            result++;
        }
        return result;
    }

    public void print() {
        for (Alumn a : array) {
            if (a != null)
                System.out.println(a.toString());
        }
    }

    public Alumn[] getAlumns() {
        return array;
    }

    public int getRegistries() {
        return registries;
    }
}
