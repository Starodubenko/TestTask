package com.epam.star.listener;

import com.epam.star.logic.ProgrammLogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandListener {

    private Map<String, String> commands = new HashMap<>();

    public CommandListener() {
        commands.put("exit","Close this program.");
        commands.put("open","Activate service for getting couples from file.");
        commands.put("add","Activate service for adding a new couple.");
        commands.put("remove","Activate service for removing couple by key.");
        commands.put("clear","Activate service for removing all couples from list.");
        commands.put("sort","Activate service for sorting couple by key or value.");
        commands.put("filter","Activate service for filtering couple by key or value.");
        commands.put("1","Activate service for to obtain couples from file.");
        commands.put("2","Activate service for to save couples to file.");
    }

    public Map<String, String> getCommands() {
        return commands;
    }

    ProgrammLogic progLog = new ProgrammLogic();

    public void listen(String command){


        switch (command.toLowerCase()) {
            case "help":  progLog.getHelpInfo(commands);
                break;
            case "exit":  progLog.exit();
                break;
            case "1":  progLog.getCouplesFromFile();
                break;
            case "2":  progLog.saveCouplesToFile();
                break;
            case "add":  progLog.addCouple();
                break;
            case "remove":  progLog.removeCouple();
                break;
            case "clear":  progLog.removeAllCouples();
                break;
            case "sort":  progLog.sort();
                break;
            case "open":  progLog.getCouplesFromFile();
                break;
            case "save":  progLog.saveCouplesToFile();
                break;
            case "filter":  progLog.filter();
                break;
            case "show":  progLog.showAll();
                break;
            default: System.out.println("This command not exist!");
        }
    }

    private List<String> parse(String s){
        List<String> result = new ArrayList<>();

        s.split(" ");

        return result;
    }
}
