import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

class Student {
    String id;
    String name;
    int age;

    public Student(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

class StudentManagementSystem {
    private ArrayList<Student> studentList;

    public StudentManagementSystem() {
        studentList = new ArrayList<>();
    }

    public void addStudent(Student student) {
        studentList.add(student);
        JOptionPane.showMessageDialog(null, "Student added successfully.");
    }

    public void removeStudent(String studentId) {
        Iterator<Student> iterator = studentList.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.getId().equals(studentId)) {
                iterator.remove();
                JOptionPane.showMessageDialog(null, "Student removed successfully.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Student not found with ID: " + studentId);
    }

    public void updateStudent(String studentId, String newName, int newAge) {
        for (Student student : studentList) {
            if (student.getId().equals(studentId)) {
                student.name = newName;
                student.age = newAge;
                JOptionPane.showMessageDialog(null, "Student updated successfully.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Student not found with ID: " + studentId);
    }

    public String displayAllStudents() {
        StringBuilder result = new StringBuilder();
        if (studentList.isEmpty()) {
            result.append("No students found.");
        } else {
            result.append("List of Students:\n");
            for (Student student : studentList) {
                result.append(student).append("\n");
            }
        }
        return result.toString();
    }
}

class StudentManagementSystemGUI {
    private JFrame frame;
    private StudentManagementSystem system;

    public StudentManagementSystemGUI() {
        frame = new JFrame("Student Management System");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(4,2));

        system = new StudentManagementSystem();

        JButton addButton = new JButton("Add Student");
        JButton removeButton = new JButton("Remove Student");
        JButton updateButton = new JButton("Update Student");
        JButton displayButton = new JButton("Display All Students");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeStudent();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateStudent();
            }
        });

        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayAllStudents();
            }
        });

        frame.add(addButton);
        frame.add(removeButton);
        frame.add(updateButton);
        frame.add(displayButton);

        frame.setVisible(true);
    }

    private void addStudent() {
        String id = JOptionPane.showInputDialog("Enter student ID:");
        String name = JOptionPane.showInputDialog("Enter student name:");
        String ageStr = JOptionPane.showInputDialog("Enter student age:");
        try {
            int age = Integer.parseInt(ageStr);
            system.addStudent(new Student(id, name, age));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid age format. Please enter a valid number.");
        }
    }

    private void removeStudent() {
        String studentId = JOptionPane.showInputDialog("Enter student ID to remove:");
        system.removeStudent(studentId);
    }

    private void updateStudent() {
        String studentId = JOptionPane.showInputDialog("Enter student ID to update:");
        String newName = JOptionPane.showInputDialog("Enter new name:");
        String newAgeStr = JOptionPane.showInputDialog("Enter new age:");
        try {
            int newAge = Integer.parseInt(newAgeStr);
            system.updateStudent(studentId, newName, newAge);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid age format. Please enter a valid number.");
        }
    }

    private void displayAllStudents() {
        String result = system.displayAllStudents();
        JOptionPane.showMessageDialog(null, result);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentManagementSystemGUI());
    }
}