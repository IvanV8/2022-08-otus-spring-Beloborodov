package ru.otus.spring082022.Beloborodov02.repositary;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.spring082022.Beloborodov02.domain.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class QuestionDAOImpl implements QuestionDAO {


    @Value("${questions.path}")
    private String csvPath;


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
    public List<Question> getAllQuestions(int maxNumberOfQuestions) {
        List<Question> questions = new ArrayList<>();

        InputStream is = getFileFromResourceAsStream(csvPath);

        try (InputStreamReader streamReader =
                     new InputStreamReader(is, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {

            // read csv file
            Iterator<CSVRecord> records = CSVFormat.DEFAULT.parse(reader).iterator();
            int i = 1;
            while (records.hasNext() && (i <= maxNumberOfQuestions || maxNumberOfQuestions == 0)) {
                CSVRecord r = records.next();
                Question q = new Question(r.get(0), r.get(1));
                questions.add(q);
                i++;
            }

        } catch (IOException e) {
            throw new InOutQuestionException(e);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Wrong format of file");
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Could not parse fields from line");
        }
        return questions;
    }


}
