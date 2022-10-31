package Evaluation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteToCSV {

    /**
     * writes Data to a CSV
     * @param data measured execution time
     * @param typeOfData title for csv: Connection (PSQL, PolyPGI, PolyJdbc); Query Number (Q1); Nbr of Executions (E10000)
     * @throws IOException when something goes wrong with creating a filewriter
     */
    public void writeToCSV (ArrayList<Long> data, String typeOfData) throws IOException {
        System.out.println("started writing to csv file");

        //typeOfData: Connection (PSQL, PolyPGI, PolyJdbc); Query Number (Q1); Nbr of Executions (E10000)
        String filename = typeOfData + ".csv";
        String path = "C:\\Users\\esigu\\SynologyDrive\\01_Uni\\UniBasel\\22Polypheny\\EvalutaionData\\" + filename;
        File file= new File(path);
        FileWriter filewriter = new FileWriter(file);
        //filewriter.append("execution_time_in_nanosecs");
        //filewriter.append(',');

        for (int i = 0; i < data.size(); i++) {
            String value = String.valueOf(data.get(i)*0.000001);    //millisecs
            filewriter.append(value);
            filewriter.append(' ');
        }

        filewriter.flush();
        filewriter.close();

        System.out.println("finished writing to file");

    }
}
