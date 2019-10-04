package com.kishinskiy.main;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Shell {

    String cmd(String command) throws IOException, InterruptedException {
        //Build command
//        List<String> commands = new ArrayList<String>();
//        commands.add("kubectl");
//        //Add arguments
//        commands.add("get pods");
//        System.out.println(commands);
        String[] output = new String[0];

        //Run macro on target
        ProcessBuilder pb = new ProcessBuilder("bash", "-c", command);
//        pb.directory(new File("."));
        pb.redirectErrorStream(true);
        Process process = pb.start();

        //Read output
        StringBuilder out = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = null, previous = null;
        while ((line = br.readLine()) != null)
            if (!line.equals(previous)) {
                previous = line;
                out.append(line).append('\n');
                System.out.println(line);
                output = line.split("\n ");
            }

        //Check result
        if (process.waitFor() == 0) {
            System.out.println("Success!");
            System.exit(0);
        }

        //Abnormal termination: Log command parameters and output and throw ExecutionException
//        System.err.println(commands);
//        System.err.println(out.toString());
        System.exit(1);
        System.out.println(Arrays.toString(output));
        return Arrays.toString(output);
    }
}