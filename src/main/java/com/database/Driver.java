package com.database;

import com.database.cli.Cli;

public class Driver {
    private static Cli cli = new Cli();
    public static void main(String[] args) {
        cli.menu();
    }

}
