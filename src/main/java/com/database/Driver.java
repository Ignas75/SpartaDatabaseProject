package com.database;

import com.database.cli.Cli;
import com.database.employee.Employee;
import com.database.reader.Reader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Driver {
    public static void main(String[] args) throws InterruptedException {
        Cli cli = new Cli();
        cli.menu();

    }
}
