package com.epam.star;

import com.epam.star.listener.CommandListener;
import com.epam.star.logic.ProgrammLogic;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        System.out.println("Program was started ... ");
        System.out.println("Enter 'help' for to obtain list of command.");

        CommandListener cmd = new CommandListener();

        String command = "";
        Scanner in = new Scanner(System.in);
        while (!command.equals("exit")) {
            command = in.nextLine();
            cmd.listen(command);
        }
        in.close();
    }
}
