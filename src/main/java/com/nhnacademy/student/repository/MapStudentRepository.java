package com.nhnacademy.student.repository;

import com.nhnacademy.student.domain.Student;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MapStudentRepository implements StudentRepository{
    private Map<String, Student> studentsMap = new ConcurrentHashMap<>();


    @Override
    public void save(Student student) {
        String studentId = student.getId();

        if(studentsMap.containsKey(studentId)){
            log.warn("이미 존재하는 학생 ID로 저장시도 : {}", studentId);
            throw new IllegalArgumentException("이미 존재하는 학생입니다: " + studentId);
        }

        studentsMap.put(studentId, student);
        log.info("새로운 학생 정보 저장 완료 - ID: {}, 이름: {}", studentId, student.getName());
    }

    @Override
    public void update(Student student) {
        String studentId = student.getId();

        // 수정할 학생 ID가 없음
        if(!studentsMap.containsKey(studentId)){
            log.error("수정할 학생이 존재하지 않습니다 : {}", studentId);
            throw new IllegalStateException("수정할 학생이 존재하지 않습니다: " + studentId);
        }

        Student oldStudent = studentsMap.get(studentId);
        studentsMap.put(studentId, student);

        log.info("학생 정보 수정 완료 - ID: {}, 이전 이름: {}, 새로운 이름: {}",
                studentId, oldStudent.getName(), student.getName());
    }

    @Override
    public void deleteById(String id) {
        // 삭제할 학생 ID가 없음
        if(!studentsMap.containsKey(id)){
            log.error("삭제할 학생이 존재하지 않습니다 : {}", id);
            throw new IllegalStateException("삭제할 학생이 존재하지 않습니다: " + id);
        }

        Student deletedStudent = studentsMap.remove(id);
        log.info("학생 정보 삭제 완료 - ID: {}, 이름: {}", id, deletedStudent.getName());
    }

    @Override
    public Student getStudentById(String id) {
        Student student = studentsMap.get(id);

        if(student != null){
            log.debug("학생 정보 조회 성공 - ID: {}, 이름: {}", id, student.getName());
        }else{
            log.debug("학생 정보 조회 실패 - ID: {}", id);
        }
        return student;
    }

    @Override
    public List<Student> getStudents() {
        // 안전한 값 보내기 (단순히 조회목적이기 때문)
        List<Student> studentList = new ArrayList<>(studentsMap.values());

        log.debug("전체 학생 목록 조회 완료 - 총 {}명", studentList.size());

        return studentList;
    }

    @Override
    public boolean existById(String id) {
        boolean exists = studentsMap.containsKey(id);
        log.debug("학생 존재 여부 확인 - ID: {}, 존재: {}", id, exists);
        return exists;
    }
}
