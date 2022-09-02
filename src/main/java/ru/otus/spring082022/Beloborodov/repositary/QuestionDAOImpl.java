package ru.otus.spring082022.Beloborodov.repositary;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import ru.otus.spring082022.Beloborodov.domain.Question;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class QuestionDAOImpl implements QuestionDAO {
    private final String csvPath;

    public QuestionDAOImpl(String filePath) {

        csvPath = filePath;
    }


    @Override
    public List<Question> getAll() {
        List<Question> questions = new ArrayList<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(csvPath))) {

            // read csv file
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(reader);
            for (CSVRecord record : records) {
                Question q = new Question(record.get(0), record.get(1));
                questions.add(q);
            }


        } catch (FileNotFoundException ex) {
            System.out.println(ex.toString());
            System.out.println("Could not find  file " + csvPath);
        } catch (IOException ex) {
            System.out.println(ex.toString());
            System.out.println("Could not read from  file " + csvPath);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.toString());
            System.out.println("Could not parse fields from line");
        }

        return questions;
    }


}
