package com.netcracker.denisik.dto;

import java.util.ArrayList;
import java.util.List;

public class WriteBookDTO {
    private List<SemesterDTO> semesterEntity;

    public WriteBookDTO() {
    }

    public WriteBookDTO(List<SemesterDTO> semesterEntity) {
        this.semesterEntity = semesterEntity;
    }

    public List<SemesterDTO> getSemesterEntity() {
        return semesterEntity;
    }

    public void setSemesterEntity(List<SemesterDTO> semesterEntity) {
        this.semesterEntity = semesterEntity;
    }

    public String toString() {
        int sem = semesterEntity.get(0).getSem();
        StringBuilder s = new StringBuilder("Оценки за семестр " + sem + " :\n");
        for (int i = 0; i < semesterEntity.size(); i++) {
            if (sem == semesterEntity.get(i).getSem()) {
                s.append("Предмет: ").append(semesterEntity.get(i).getSubject())
                        .append(";  Оценка: ")
                        .append(semesterEntity.get(i).getMark()).append("\n");
            } else {
                sem = semesterEntity.get(i).getSem();
                s.append("Оценки за семестр ").append(sem).append(" :\n")
                        .append("Предмет: ").append(semesterEntity.get(i).getSubject())
                        .append(";  Оценка: ")
                        .append(semesterEntity.get(i).getMark()).append("\n");
            }
        }
        return s.toString();
    }
}
