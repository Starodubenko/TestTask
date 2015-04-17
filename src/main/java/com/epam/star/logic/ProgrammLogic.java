package com.epam.star.logic;

import java.io.*;
import java.util.*;

public class ProgrammLogic {

    private Map<String, String> couples = new TreeMap<>();
    private Scanner in = new Scanner(System.in);

    public void getCouplesFromFile() {
        try {
            try(BufferedReader br = new BufferedReader(new FileReader("Couples.txt"))) {
                String line = br.readLine();

                while (line != null && !line.equals("")) {
                    String[] couple = line.split("=");
                    couples.put(couple[0], couple[1]);
                    line = br.readLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("");
        for (Map.Entry<String, String> entry : couples.entrySet()) {
            System.out.println("In couples was added:");
            System.out.println("| " + entry.getKey() + " | " + entry.getValue() + "|");
        }
    }

    public void saveCouplesToFile() {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("Couples.txt", "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        for (Map.Entry<String, String> entry : couples.entrySet()) {
            if (writer != null) {
                writer.println(entry.getKey() +"="+ entry.getValue());
            }
        }
        assert writer != null;
        writer.close();

        System.out.println("Couples was saved in Couples.txt file.:");
    }

    public void filter(){
        System.out.println("");
        System.out.println("Choose, filter by what? 'name' or 'value'");

        String subCmd = in.nextLine().toLowerCase();

        if (subCmd.equals("name")) filterByName();
        else if (subCmd.equals("value")) {filterByValue();
        } else System.out.println("Entered type is not valid!");
    }

    private void filterByName(){
        System.out.println("");
        System.out.println("Filter value:");
        String filtString = in.nextLine().toLowerCase();

        Map<String, String> filteredMap = new HashMap<>();

        for (Map.Entry<String, String> entry : couples.entrySet()) {
            if (entry.getKey().toLowerCase().contains(filtString)) filteredMap.put(entry.getKey(),entry.getValue());
        }

        System.out.println("");
        System.out.println("File contains couples: Key  =>  Value");
        for (Map.Entry<String, String> entry : filteredMap.entrySet()) {
            System.out.println("| " + entry.getKey() + " | " + entry.getValue() + "|");
        }
    }

    private void filterByValue(){
        System.out.println("");
        System.out.println("Filter value:");
        String filtString = in.nextLine().toLowerCase();

        Map<String, String> filteredMap = new HashMap<>();

        for (Map.Entry<String, String> entry : couples.entrySet()) {
            if (entry.getValue().toLowerCase().contains(filtString)) filteredMap.put(entry.getKey(),entry.getValue());
        }

        System.out.println("");
        System.out.println("File contains couples: Key  =>  Value");
        for (Map.Entry<String, String> entry : filteredMap.entrySet()) {
            System.out.println("| " + entry.getKey() + " | " + entry.getValue() + "|");
        }
    }


    public void addCouple() {
        String currentKey;
        String currentValue;

        System.out.println("Enter key: ");
        currentKey = in.nextLine();
        System.out.println("Enter value: ");
        currentValue = in.nextLine();

        couples.put(currentKey, currentValue);

        System.out.println("File contains couples: Key  =>  Value");
        for (Map.Entry<String, String> entry : couples.entrySet()) {
            System.out.println("| " + entry.getKey() + " | " + entry.getValue() + "|");
        }
    }

    public void removeCouple() {
        System.out.println("Enter key of couple, which you want to remove from list: ");

        String key = in.nextLine();
        if (couples.containsKey(key)) {
            couples.remove(key);

            System.out.println("");
            System.out.println("File contains couples: Key  =>  Value");
            for (Map.Entry<String, String> entry : couples.entrySet()) {
                System.out.println("| " + entry.getKey() + " | " + entry.getValue() + "|");
            }
        } else System.out.println("This key not exist in couples list!");
    }

    public void exit() {

    }

    public void getHelpInfo(Map<String, String> commands) {

        System.out.println("");
        System.out.println("All commands:");
        for (Map.Entry<String, String> entry : commands.entrySet()) {
            System.out.println("  - " + entry.getKey() + ": " + entry.getValue());
        }
    }

    public void removeAllCouples() {
        couples.clear();
        System.out.println("Now list of couples is empty!");
    }

    public void sort() {
        System.out.println("");
        System.out.println("Choose, sort by what? 'name' or 'value'");

        String subCmd = in.nextLine().toLowerCase();

        if (subCmd.equals("name")) sortByName();
        else if (subCmd.equals("value")) {
            sortByValue();
        } else System.out.println("Entered type is not valid!");
    }

    public void sortByName() {
        System.out.println("");
        System.out.println("Enter direction of sort: 'asc' or 'desc'");
        String direction = in.nextLine().toLowerCase();

        Map<String, String> treeMap = null;
        if (direction.equals("asc")) {
            treeMap = new TreeMap<>();
            treeMap.putAll(couples);
        }
        if (direction.equals("desc")) {
            treeMap = new TreeMap<>(Collections.reverseOrder());
            treeMap.putAll(couples);
        }

        System.out.println("");
        System.out.println("File contains sorted by key couples: Key  =>  Value");
        for (Map.Entry<String, String> entry : treeMap.entrySet()) {
            System.out.println("  - " + entry.getKey() + ": " + entry.getValue());
        }
    }

    public void sortByValue() {
        System.out.println("");
        System.out.println("Enter direction of sort: 'asc' or 'desc'");
        String direction = in.nextLine().toLowerCase();

        Map<String, String> sortedCouples = null;
        if (direction.equals("asc")) sortedCouples = sortByComparator(couples, direction);
        if (direction.equals("desc")) sortedCouples = sortByComparator(couples, direction);

        System.out.println("");
        System.out.println("File contains sorted by value couples: Key  =>  Value");
        for (Map.Entry<String, String> entry : sortedCouples.entrySet()) {
            System.out.println("  - " + entry.getKey() + ": " + entry.getValue());
        }
    }

    private static Map<String, String> sortByComparator(Map<String, String> unsortMap, String direction) {

        // Convert Map to List
        List<Map.Entry<String, String>> list = new LinkedList<>(unsortMap.entrySet());

        // Sort list with comparator, to compare the Map values
        if (direction.equals("asc"))
            Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
                public int compare(Map.Entry<String, String> o1,
                                   Map.Entry<String, String> o2) {
                    return (o1.getValue()).compareTo(o2.getValue());
                }
            });

        if (direction.equals("desc"))
            Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
                public int compare(Map.Entry<String, String> o1,
                                   Map.Entry<String, String> o2) {
                    return (o2.getValue()).compareTo(o1.getValue());
                }
            });

        // Convert sorted map back to a Map
        Map<String, String> sortedMap = new TreeMap<>();
        for (Iterator<Map.Entry<String, String>> it = list.iterator(); it.hasNext(); ) {
            Map.Entry<String, String> entry = it.next();
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

    public void showAll() {
        System.out.println("File contains couples: Key  =>  Value");
        for (Map.Entry<String, String> entry : couples.entrySet()) {
            System.out.println("| " + entry.getKey() + " | " + entry.getValue() + "|");
        }
        if (couples.isEmpty()) System.out.println("Couples is empty !!!");
    }
}
