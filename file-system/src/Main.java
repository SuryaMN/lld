public class Main {

    public static void main(String[] args) {
        Directory root = new Directory("home", null);
        FileSystem fs = new FileSystem(root);

        fs.createFolder("/home", "surya");
        fs.createFile("/home/surya/", "temp.txt", "Hello world!");
        fs.createFolder("/home","aditi");
        fs.moveFileSystemEntry("/home/surya/temp.txt", "/home/aditi");

        System.out.println("File content --> " + fs.getFileContent("/home/aditi/temp.txt"));
    }
}
