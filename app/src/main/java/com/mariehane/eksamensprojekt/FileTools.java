package com.mariehane.eksamensprojekt;

import java.io.BufferedReader;
import java.io.IOException;

public class FileTools {
    /**
     * Reads a specific line from a file (index starts from zero, so line 1 is lineNumber=0)
     */
    public static String readLine(BufferedReader br, int lineNumber) throws IOException {
        while (lineNumber > 0) {
            lineNumber--;
            br.readLine();
        }
        return br.readLine();
    }
}
