package com.unibh;

import java.util.Scanner;

public class Alumn {

    private long ra;
    private String name;
    private String birthDate;

    public Alumn(long ra, String name, String birthDate) {
        this.ra = ra;
        this.name = name;
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Alumn {" +
                "ra='" + ra + '\'' +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }

    public static Alumn createAlumn() {
        final Scanner inputStr = new Scanner(System.in);
        final Scanner inputNum = new Scanner(System.in);
        long ra;
        String name, birthday;

        System.out.print("\n\n[Name]: ");
        name = inputStr.nextLine();

        System.out.print("[RA]: ");
        ra = inputNum.nextLong();

        System.out.print("[BirthDay]: ");
        birthday = inputStr.nextLine();

        return new Alumn(ra, name, birthday);
    }

    public long getRa() {
        return ra;
    }

    public String getName() {
        return name;
    }

}
