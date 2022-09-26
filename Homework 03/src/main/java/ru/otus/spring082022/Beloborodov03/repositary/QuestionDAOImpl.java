package ru.otus.spring082022.Beloborodov03.repositary;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.spring082022.Beloborodov03.appconfig.QuestionsPathProvider;
import ru.otus.spring082022.Beloborodov03.domain.Question;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class QuestionDAOImpl implements QuestionDAO {

    private final QuestionsPathProvider qustionDataProvider;

    @Autowired
    public QuestionDAOImpl(QuestionsPathProvider qustionDataProvider) {
        this.qustionDataProvider = qustionDataProvider;
    }

    private InputStream getFileFromResourceAsStream(String fileName) {

        // The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("File not found! " + fileName);
        } else {
            return inputStream;
        }
    }

    @Override
    public List<Question> getAllQuestions(int maxNumberOfQuestions) {
        List<Question> questions = new ArrayList<>();
        // получение имени файла - ресурса
        InputStream is = getFileFromResourceAsStream(qustionDataProvider.getQuestionsPath());

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

        } catch (Exception e) {
            throw new QuestionException(e.getMessage(), e);
        }
        return questions;
    }


}
