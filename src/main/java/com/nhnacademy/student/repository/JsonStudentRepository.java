package com.nhnacademy.student.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nhnacademy.student.domain.Student;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class JsonStudentRepository implements StudentRepository {
    private final ObjectMapper objectMapper;
    //json file 저장 경로
    private static final String JSON_FILE_PATH="/Users/chosun-nhn47/Desktop/webApplication/student/src/main/resources/json/student.json";

    public JsonStudentRepository() throws IOException {
        objectMapper = new ObjectMapper();
        //LocalDatetime json 직열화/역직렬화 가능하도록 설정
        objectMapper.registerModule(new JavaTimeModule());
        //todo JSON_FILE_PATH 경로에 json 파일이 존재하면 삭제 합니다.
        File file = new File(JSON_FILE_PATH);
        if(file.exists()){
            file.delete();
        }
    }

    private synchronized List<Student> readJsonFile(){
        //todo json 파일이 존재하지 않다면 비어있는 List<Student> 리턴
        File file = new File(JSON_FILE_PATH);
        if(!file.exists()){
            return new ArrayList<>();
        }
        //json read & 역직렬화 ( json string -> Object )
        try(FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        ) {
            List<Student> students;
            students = objectMapper.readValue(bufferedReader, new TypeReference<List<Student>>() {});
            return  students;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private synchronized void writeJsonFile(List<Student> studentList){
        // List<Student> 객체를 -> json 파일로 저장 : 직렬화
        File file = new File(JSON_FILE_PATH);

        try(
                FileWriter fileWriter = new FileWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        ) {
            objectMapper.writeValue(bufferedWriter,studentList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Student student) {
        //json String -> Object 형태로 변화 (직렬화)
        List<Student> students = readJsonFile();
        //List에 student 추가
        students.add(student);
        //List<Student>객체를 -> json String 형태로 저장(직렬화)
        writeJsonFile(students);
    }

    @Override
    public void update(Student student) {
        List<Student> students = readJsonFile();

        for (Student changeStudent : students) {
            if(changeStudent.getId().equals(student.getId())){
                changeStudent.setName(student.getName());
                changeStudent.setGender(student.getGender());
                changeStudent.setAge(student.getAge());
            }
        }
        writeJsonFile(students);
    }

    @Override
    public void deleteById(String id) {
        List<Student> students = readJsonFile();
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);

            if(student.getId().equals(id)){
                students.remove(i);
                return;
            }
        }

        writeJsonFile(students);
    }

    @Override
    public Student getStudentById(String id) {
        List<Student> students = readJsonFile();
        for (Student student : students) {
            if(student.getId().equals(id)){
                return student;
            }
        }
        return null;
    }

    @Override
    public List<Student> getStudents() {
        List<Student> studentList = readJsonFile();
        return List.copyOf(studentList);    // 안전한 복사
    }

    @Override
    public boolean existById(String id) {
        List<Student> studentList = readJsonFile();
        for (Student student : studentList) {
            if(student.getId().equals(id)){
                return true;
            }
        }
        return false;
    }
}