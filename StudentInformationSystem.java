import java.util.*;

public class StudentInformationSystem {
    private List<Student> students = new ArrayList<>();
    private List<Course> courses = new ArrayList<>();
    private List<Enrollment> enrollments = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
        System.out.println("Student added successfully!");
    }

    public void addCourse(Course course) {
        courses.add(course);
        System.out.println("Course added successfully!");
    }

    public void enrollStudent(String studentId, String courseId, Scanner sc) {
        // Find student and course
        Student student = students.stream()
                .filter(s -> s.getStudentId().equals(studentId))
                .findFirst()
                .orElse(null);

        Course course = courses.stream()
                .filter(c -> c.getCourseId().equals(courseId))
                .findFirst()
                .orElse(null);

        if (student == null || course == null) {
            System.out.println("Invalid student ID or course ID.");
            return;
        }

        if (student instanceof GraduateStudent) {
            // Graduate students can only enroll in one course
            boolean alreadyEnrolled = enrollments.stream()
                    .anyMatch(e -> e.getStudent().getStudentId().equals(studentId));
            if (alreadyEnrolled) {
                System.out.println("Graduate students can only enroll in one course.");
            } else {
                System.out.print("Enter Score for Graduate Student: ");
                double score = sc.nextDouble();
                Enrollment enrollment = new Enrollment(student, course, score);
                enrollments.add(enrollment);
                System.out.println("Graduate student enrolled successfully with score!");
            }
        } else {
            // Non-graduate students can enroll in multiple courses
            Enrollment enrollment = new Enrollment(student, course, 0.0); // Default score for non-grads
            enrollments.add(enrollment);
            System.out.println("Non-graduate student enrolled successfully!");
        }
    }

    public void displayEnrollments() {
        System.out.println("\n--- Enrollment Details ---");
        if (enrollments.isEmpty()) {
            System.out.println("No enrollments found.");
            return;
        }

        for (Enrollment e : enrollments) {
            double grade = e.calculateGrade(e.getScore());
            System.out.println("Student: " + e.getStudent().getName());
            System.out.println("Course: " + e.getCourse().getCourseName());
            System.out.println("Score: " + e.getScore());
            System.out.println("Calculated Grade: " + grade);
            System.out.println("--------------------------");
        }
    }

    public static void main(String[] args) {
        StudentInformationSystem system = new StudentInformationSystem();
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("\n1. Add Student\n2. Add Course\n3. Enroll Student\n4. Display Enrollments\n5. Exit");
                System.out.print("Select an Option Above: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter Student ID: ");
                        String studentId = sc.next();
                        System.out.print("Enter Name: ");
                        String name = sc.next();
                        System.out.print("Is Graduate Student? (yes/no): ");
                        String grad = sc.next();
                        if (grad.equalsIgnoreCase("yes")) {
                            system.addStudent(new GraduateStudent(studentId, name));
                        } else {
                            system.addStudent(new Student(studentId, name));
                        }
                        break;

                    case 2:
                        System.out.print("Enter Course Code: ");
                        String courseId = sc.next();
                        System.out.print("Enter Course Name: ");
                        String courseName = sc.next();
                        system.addCourse(new Course(courseId, courseName));
                        break;

                    case 3:
                        System.out.print("Enter Student ID: ");
                        String sId = sc.next();
                        System.out.print("Enter Course ID: ");
                        String cId = sc.next();
                        system.enrollStudent(sId, cId, sc);
                        break;

                    case 4:
                        system.displayEnrollments();
                        break;

                    case 5:
                        System.out.println("Exiting...");
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter the correct type.");
                sc.nextLine(); // Clear invalid input
            }
        }
    }
}

// Class Definitions

class Course {
    private String courseId;
    private String courseName;

    public Course(String courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }
}

class Enrollment implements Grading {
    private Student student;
    private Course course;
    private double score;

    public Enrollment(Student student, Course course, double score) {
        this.student = student;
        this.course = course;
        this.score = score;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public double calculateGrade(double score) {
        if (score >= 90) return 1.0;
        else if (score >= 80) return 2.0;
        else if (score >= 75) return 3.0;
        else if (score >= 60) return 4.0;
        else return 0.0;
    }
}

interface Grading {
    double calculateGrade(double score);
}

class GraduateStudent extends Student {
    public GraduateStudent(String studentId, String name) {
        super(studentId, name);
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Type: Graduate Student");
    }
}

class Student {
    private String studentId;
    private String name;

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public void displayInfo() {
        System.out.println("Student ID: " + studentId);
        System.out.println("Name: " + name);
    }
} 