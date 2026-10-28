package group_5_attendease;

public class Node_subject {

    String subject;
    String subjectCode;

    //Class day
    String day;

    // Lecture schedule
    String lectureStart;
    String lectureEnd;

    // Optional laboratory schedule
    boolean hasLab;
    String labStart;
    String labEnd;

    Node_subject prev;
    Node_subject next;

    Node_attendance attendanceHead;
    Node_attendance attendanceTail;

    // Subjects with complete schedule information
    Node_subject(String code, String subject, String day,
            String lectureStart, String lectureEnd,
            boolean hasLab, String labStart, String labEnd) {

        this.subjectCode = code;
        this.subject = subject;
        this.day = day;
        this.lectureStart = lectureStart;
        this.lectureEnd = lectureEnd;
        this.hasLab = hasLab;
        this.labStart = labStart;
        this.labEnd = labEnd;

        prev = null;
        next = null;
        attendanceHead = null;
        attendanceTail = null;
    }
}
