package ru.otus.spring082022.Beloborodov01.repositary;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import ru.otus.spring082022.Beloborodov01.domain.Question;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class QuestionDAOImpl implements QuestionDAO {
    private final String csvPath;

    public QuestionDAOImpl(String filePath) {

        csvPath = filePath;
    }

    private InputStream getFileFromResourceAsStream(String fileName) {

        // The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }

    }

    @Override
    public List<Question> getAll() throws FileNotFoundException, IOException {
        List<Question> questions = new ArrayList<>();


        InputStream is = getFileFromResourceAsStream(csvPath);

        try (InputStreamReader streamReader =
                     new InputStreamReader(is, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {

            // read csv file
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(reader);
            for (CSVRecord record : records) {
                Question q = new Question(record.get(0), record.get(1));
                questions.add(q);
            }

        } catch (FileNotFoundException ex) {
            throw new FileNotFoundException("file not found! " + csvPath);
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Could not parse fields from line");
        }
        return questions;
    }


}
