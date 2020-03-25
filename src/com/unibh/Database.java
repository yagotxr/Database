package com.unibh;

import java.util.logging.Logger;

import static java.util.Objects.isNull;

public class Database {

    private final Logger logger = Logger.getLogger("Database");
    private Alumn[] alumns;
    private int registries;

    public Database(int nRegistries) {
        this.alumns = new Alumn[nRegistries];
    }

    public Alumn insert(Alumn alumn){
        for(int i = 0; i < alumns.length; i++){
            if(isNull(alumns[i])){
                alumns[i] = alumn;
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
        for(Alumn a : alumns){
            if(a.getRa() == ra){
                logger.info("Comparations made: " + iterations + " times.");
                return a;
            }
            iterations++;
        }
        return null;
    }

    public static void orderDatabase(Alumn[] array, long registries){
        Alumn less;
        int position = 0;
        for(int i = 0; i < registries; i++){
            less = array[i];
            for(int j = i; j < registries; j++){
                    if (array[j].getRa() < less.getRa()) {
                        less = array[j];
                        position = j;
                    }
                }
            array[position] = array[i];
            array[i] = less;
        }
    }

    public void print(){
        for (Alumn a : alumns){
            if(a != null)
            System.out.println(a.toString());
        }
    }

    public Alumn[] getAlumns() {
        return alumns;
    }

    public int getRegistries() {
        return registries;
    }
}
