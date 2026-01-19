import java.util.Collection;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class Service {

  public void addStudent(Student student) throws IOException {
    var f = new FileWriter("db.txt", true);
    var b = new BufferedWriter(f);
    b.append(student.ToString());
    b.newLine();
    b.close();
  }

  public Collection<Student> getStudents() throws IOException {
    var ret = new ArrayList<Student>();
    var f = new FileReader("db.txt");
    var reader = new BufferedReader(f);
    String line = "";
    while (true) {
      line = reader.readLine();
      if(line == null)
        break;
      ret.add(Student.Parse(line));
    }
    reader.close();
    return ret;
  }
  

  public Student findStudentByName(String name) throws IOException {
      var students = getStudents();
      for(Student s : students) {
          if(s.GetName().equals(name)) {
              return s;
          }
      }
      return null;
  }
  
  private void rewriteFile(ArrayList<Student> students) throws IOException {
      var f = new FileWriter("db.txt", false);
      var b = new BufferedWriter(f);
      for(Student s : students) {
          b.write(s.ToString());
          b.newLine();
      }
      b.close();
  }

  public boolean deleteStudentByName(String name) throws IOException {
      var students = getStudents();
      var studentToRemove = findStudentByName(name);

      if(studentToRemove != null) {
          var newStudents = new ArrayList<Student>();
          for(Student s : students) {
              if(!s.GetName().equals(name)) {
                  newStudents.add(s);
              }
          }
          rewriteFile(newStudents);
          return true;
      }
      return false;
  }
}