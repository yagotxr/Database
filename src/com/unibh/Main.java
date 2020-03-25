package com.unibh;

import java.time.LocalDate;
import java.util.List;

import static com.unibh.Database.orderDatabase;

public class Main {

    public static void main(String[] args) {

        Database database = new Database(250);

        database.insert(new Alumn(
                9,
                "A",
                LocalDate.parse("1997-04-16")
        ));
        database.insert(new Alumn(
                3,
                "b",
                LocalDate.parse("1997-04-16")
        ));
        database.insert(new Alumn(
                6,
                "c",
                LocalDate.parse("1997-04-16")
        ));
        database.insert(new Alumn(
                4,
                "d",
                LocalDate.parse("1997-04-16")
        ));
        database.insert(new Alumn(
                8,
                "e",
                LocalDate.parse("1997-04-16")
        ));
        database.insert(new Alumn(
                1,
                "f",
                LocalDate.parse("1997-04-16")
        ));
        database.insert(new Alumn(
                2,
                "g",
                LocalDate.parse("1997-04-16")
        ));
        database.insert(new Alumn(
                5,
                "h",
                LocalDate.parse("1997-04-16")
        ));
        database.print();

        orderDatabase(database.getAlumns(), database.getRegistries());

        System.out.println("ordered");
        database.print();

    }
}
