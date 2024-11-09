    package com.studentapp;

    import java.util.*;

    public class Main {
        private static List<Student> studentList;
        private static Scanner scanner;

        public static void main(String[] args) {
            studentList = new ArrayList<Student>();
            scanner = new Scanner(System.in);

            System.out.println("************** Student Management System **************");

            while(true) {
                System.out.println("************** Welcome ***************");
                System.out.println("Select an option....");
                System.out.println("1. Register a Student");
                System.out.println("2. Find Student with student id");
                System.out.println("3. List All Student Information");
                System.out.println("4. List Student Information in Sorted Order");
                    System.out.println("5. Exit");

                    int option = scanner.nextInt();
                    switch (option) {
                        case 1:
                            enrollStudent(scanner);
                            break;
                        case 2:
                            findStudentById(scanner);
                            break;
                        case 3:
                            printAllStudentData();
                            break;
                        case 4:
                            sortByName();
                            break;
                        case 5:
                            exit();
                            break;
                        default:
                            System.out.println("Invalid option selected!!! Choose between 1 to 5");
                    }
                }
            }

            private static void enrollStudent(Scanner scanner) {
                System.out.println("Enter the student name: ");
                String studentName = scanner.next();

                System.out.println("Enter the student age: ");
                int studentAge = scanner.nextInt();

                System.out.println("Enter the student id: ");
                String studentId = scanner.next();

                Student newStudent = new Student(studentName, studentAge, studentId);
                studentList.add(newStudent);

                while(true) {
                    System.out.println("Enter the course to be enrolled or enter done to exit: ");
                    String courseName = scanner.next();
                    if(courseName.equalsIgnoreCase("done")) {
                        break;
                    }
                    newStudent.enrollCourse(courseName);
                }
                newStudent.printStudentInfo();
            }

            private static void printAllStudentData() {
                if(!studentList.isEmpty()) {
                    System.out.println("----------------All Student Data-----------------");
                    for(Student student : studentList) {
                        student.printStudentInfo();
                    }
                    System.out.println("-------------------------------------------------");
                }
                else {
                    System.err.println("Student List is Empty!!! No Student record found!!!");
                }
            }

            private static void findStudentById(Scanner scanner) {
                Student studentFound = null;
                System.out.println("Enter student id: ");
                String studentId = scanner.next();

                try {
                    studentFound = studentList.stream()
                            .filter(student -> student.getStudentId().equalsIgnoreCase(studentId))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("No Data found !!!"));
                    studentFound.printStudentInfo();
                }
                catch(RuntimeException e) {
                    System.err.println("Student with ID " + studentId + " not found !!!");
                }
            }

            private static Student findStudentById(String studentId) {
                Student result = null;
                try {
                    result = studentList.stream()
                            .filter(student -> student.getStudentId().equalsIgnoreCase(studentId))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("No Data found !!!"));
                }
                catch(RuntimeException e) {
                    System.err.println("Student with ID " + studentId + " not found !!!");
                }
                return result;
            }

            private static void sortByName() {
                Comparator<Student> studentNameComparator = (o1, o2) -> o1.getName().compareTo(o2.getName());
                Collections.sort(studentList, studentNameComparator);
                printAllStudentData();
            }

            private static void exit() {
                System.out.println("Application is stopped its execution....");
                System.exit(0);
            }
    }

