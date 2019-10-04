package com.kishinskiy.main;

import io.kubernetes.client.ApiException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ApiException {
        Kubectl kubectl = new Kubectl();
        try {
            System.out.println(kubectl.getPods("default"));
        }catch (NullPointerException ex){
            System.out.println("ERR!: Method Return Null");
        }
    }
}
