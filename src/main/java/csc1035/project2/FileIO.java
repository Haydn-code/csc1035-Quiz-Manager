package csc1035.project2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FileIO {
    String FilePath = "exports";

    public FileIO() {
        File directory = new File(FilePath);
        if (! directory.exists()){ //check exports directory exists
            directory.mkdir(); //create directory
        }
    }

    public String getFilePath() {
        return FilePath;
    }

    public void setFilePath(String filePath) {
        FilePath = filePath;
    }

    public boolean ExportQuestions(List<Question> Questions, String FileName) throws IOException {
        ObjectMapper jsonMapper = new ObjectMapper();
        if(FileName == ""){ //check that filename passed in method is not empty
            FileName = "export-questions-"+ LocalDate.now(); //set standard file name when empty
        }
        if(Files.isWritable(Path.of(FilePath))){ //check file path has permission to write
            jsonMapper.writeValue(new File(FilePath+"/"+FileName+".json"),Questions);
            return true;
        }else{
            return false;
        }
    }
    public List<Question> ImportQuestions(String FileLocation) throws IOException, Exception{
        ObjectMapper jsonMapper = new ObjectMapper();
        File f = new File(FileLocation);
        if(f.exists() && !f.isDirectory()) {
            // do something
            return jsonMapper.readValue(f, new TypeReference<List<Question>>(){});
        }else{
            return Collections.emptyList();
        }
    }
    public void mnuExport(List<Question> questions){
        System.out.println("\n--Question Export--");
        System.out.println("file will be exported in JSON format to the 'exports' directory.");
        System.out.println("\nEnter File Name:  (leave this blank if you want to use the default)");
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();
        try {
            ExportQuestions(questions,fileName);
            System.out.println("Successfully exported questions");
        } catch (IOException IOE){
            System.out.println("Failed to export questions to file.");
        }
    }
    public void mnuImport(){
        System.out.println("\n--Question Import--");
        System.out.println("Choose a json file you wish to import questions from. To import from the exports directory, type 'exports/{you-file-name-here}'");
        System.out.println("\n File:");
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();
        try{
            ImportQuestions(fileName);
            System.out.println("Successfully imported questions from "+fileName);
        } catch (Exception E){
            System.out.println("failed to import questions from "+fileName+"\nDetails: "+E);
        }
    }

}
