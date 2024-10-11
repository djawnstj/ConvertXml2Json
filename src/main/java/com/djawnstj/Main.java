package com.djawnstj;

import com.djawnstj.xml.Xmls;

public class Main {
    public static void main(String[] args) {
        final Xmls xmls = new Xmls();
        xmls.toJsonStrings();
        xmls.saveToJson();
    }
}
