package com.unibh;

import java.util.Scanner;

import static com.unibh.Alumn.createAlumn;
import static java.util.Objects.isNull;

public class App {
    private final Scanner inputStr = new Scanner(System.in);
    private final Scanner inputNum = new Scanner(System.in);
    private Database db = new Database(250);
    private int opt;

    public void init() {
        System.out.println("============[School]============");
        while (true) {
            System.out.println("\n\n\n" +
                    "[1] Insert Alumn \n" +
                    "[2] Insert Test Alumn List \n" +
                    "[3] Find and Remove\n" +
                    "[4] Print Database \n" +
                    "[5] Order Database \n" +
                    "[6] Reset Database \n" +
                    "[0] Exit"
            );

            System.out.print("[opt]> ");
            opt = inputStr.nextInt();
            switch (opt) {
                case 1:
                    db.insert(createAlumn());
                    break;
                case 2:
                    insertData(db);
                    break;
                case 3:
                    searchAlumn();
                    break;
                case 4:
                    db.print();
                    break;
                case 5:
                    db.order();
                    break;
                case 6:
                    db.reset();
                    break;
                case 0:
                    System.exit(0);
            }
        }
    }

    private void searchAlumn() {
        opt = 0;
        Alumn result;

        System.out.println("\n\n" +
                "[1] RA \n" +
                "[2] Name \n" +
                "<-[Back]");
        System.out.print("\n[opt]> ");
        opt = inputNum.nextInt();
        switch (opt) {
            case 1:
                System.out.print("[RA]> ");
                result = db.find(inputNum.nextLong());
                if (result != null) {
                    removeAlumn(result);
                } else {
                    System.out.println("404 - Not Found");
                }
                break;
            case 2:
                System.out.print("[Name]> ");
                result = db.find(inputStr.next());
                if (result != null) {
                    removeAlumn(result);
                } else {
                    System.out.println("404 - Not Found");
                }
                break;
        }
    }

    private void removeAlumn(Alumn alumn) {
        opt = 0;

        System.out.println("\n\n" +
                "[Remove " + alumn.getName().toUpperCase() + "?]" +
                "[1] Yep \n" +
                "[-] Nop \n");

        System.out.print("\n[opt]> ");
        opt = inputNum.nextInt();

        if (opt == 1) {
            db.remove(alumn);
        }
    }

    private static void insertData(Database database) {
        database.insertAll(
                new Alumn(
                        9,
                        "a",
                        "1997-04-01"),
                new Alumn(
                        3,
                        "b",
                        "1997-04-02"),
                new Alumn(
                        6,
                        "c",
                        "1997-04-03"),
                new Alumn(
                        4,
                        "d",
                        "1997-04-04"),
                new Alumn(
                        8,
                        "e",
                        "1997-04-05"),
                new Alumn(
                        1,
                        "f",
                        "1997-04-06"),
                new Alumn(
                        2,
                        "g",
                        "1997-04-07"),
                new Alumn(
                        5,
                        "h",
                        "1997-04-08"),

                new Alumn(
                        7,
                        "i",
                        "1997-04-09"
                )
        );
    }
}
