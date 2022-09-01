package ru.otus.spring082022.Beloborodov.repositary;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import ru.otus.spring082022.Beloborodov.domain.Question;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


public class QuestionDAOImpl implements QuestionDAO {
    private String csvPath;

    public QuestionDAOImpl(String filePath) {
        csvPath = filePath;
    }

    @Override
    public ArrayList<Question> getAll() {
        ArrayList<Question> questions = new ArrayList<>();
        try {
            // create a reader
            Reader reader = Files.newBufferedReader(Paths.get(csvPath));
            // read csv file
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(reader);
            for (CSVRecord record : records) {
                Question q = new Question(record.get(0), record.get(1));
                questions.add(q);
            }
            // close the reader
            reader.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return questions;
    }

    public void setCsvPath(String csvPath) {
        this.csvPath = csvPath;
    }
}
