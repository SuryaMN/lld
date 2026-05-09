import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class FileSystem {

    private final Directory root;

    public FileSystem(Directory root) {
        this.root = root;
    }

    public void createFolder(String path, String name) {

        Optional<FileSystemEntry> optLeafNode = getLeafNode(path);
        if(optLeafNode.isEmpty()) {
            throw new RuntimeException("Path provided is incorrect");
        }
        FileSystemEntry leafNode = optLeafNode.get();
        if(leafNode.getClass() != Directory.class) {
            throw new RuntimeException("Invalid path");
        }
        Directory leafDir = (Directory) leafNode;
        leafDir.createDirectory(name);
        System.out.println("Created directory " + name + " inside " + path);
    }

    private Optional<FileSystemEntry> getLeafNode(String path) {

        List<String> pathList = validateAndExtractPathList(path);

        FileSystemEntry entry = root;
        for(String nodeName: pathList) {
            if(entry.getClass() != Directory.class) {
                throw new RuntimeException("Invalid path");
            }
            Directory dir = (Directory) entry;
            Optional<FileSystemEntry> optNode = dir.getChild(nodeName);
            if(optNode.isEmpty()) {
                throw new RuntimeException("Directory not found");
            }
            entry = optNode.get();
        }
        return Optional.of(entry);
    }

    private static List<String> validateAndExtractPathList(String path) {
        if(path == null || path.isBlank()) {
            throw new RuntimeException("Path provided is incorrect");
        }

        if (path.charAt(0) == '/') {
            path = path.substring(1);
        }
        if (path.charAt(path.length()-1) == '/') {
            path = path.substring(0, path.length()-1);
        }

        List<String> pathList = Arrays.stream(path.split("/")).toList();
        if(!Objects.equals(pathList.getFirst(), "home")) {
            throw new RuntimeException("Path provided is incorrect");
        }
        pathList = pathList.subList(1, pathList.size());
        return pathList;
    }

    public void createFile(String path, String name, String content) {
        Optional<FileSystemEntry> optLeafNode = getLeafNode(path);
        if(optLeafNode.isEmpty()) {
            throw new RuntimeException("Path provided is incorrect");
        }
        FileSystemEntry leafNode = optLeafNode.get();
        if(leafNode.getClass() != Directory.class) {
            throw new RuntimeException("Invalid path");
        }
        Directory leafDir = (Directory) leafNode;
        leafDir.createFile(name, content);
        System.out.println("Created file " + name + " inside " + path);
    }

//    public void deleteFileSystemEntry(String path) {
//
//    }

    public void moveFileSystemEntry(String fromPath, String toPath) {
        Optional<FileSystemEntry> optSourceLeafNode = getLeafNode(fromPath);
        if(optSourceLeafNode.isEmpty()) {
            throw new RuntimeException("Path provided is incorrect");
        }
        FileSystemEntry sourceLeafNode = optSourceLeafNode.get();
        Directory parentNode = sourceLeafNode.getParent();
        parentNode.removeChild(sourceLeafNode.getName());

        Optional<FileSystemEntry> optDestLeafNode = getLeafNode(toPath);
        if(optDestLeafNode.isEmpty()) {
            throw new RuntimeException("Path provided is incorrect");
        }
        FileSystemEntry destLeafNode = optDestLeafNode.get();
        if(destLeafNode.getClass() != Directory.class) {
            throw new RuntimeException("Path provided is incorrect");
        }
        Directory destDir = (Directory) destLeafNode;
        destDir.addChild(sourceLeafNode);

        System.out.println("Moved file from " + fromPath + " to " + toPath);
    }

//    public void renameFile(String path, String newName) {
//
//    }
//

    public String getFileContent(String path) {
        List<String> pathList = validateAndExtractPathList(path);
        List<String> dirPathList = pathList.subList(0, pathList.size()-1);
        Directory dir = root;
        for (String dirName : dirPathList) {
            Optional<FileSystemEntry> optDir = dir.getChild(dirName);
            if (optDir.isEmpty()) {
                throw new RuntimeException("Directory not found");
            }
            dir = (Directory) optDir.get();
        }

        Optional<FileSystemEntry> optChild = dir.getChild(pathList.getLast());
        if (optChild.isEmpty()) {
            throw new RuntimeException("File not found");
        }
        FileSystemEntry child = optChild.get();
        if(child.getClass() != File.class) {
            throw new RuntimeException("Invalid path");
        }

        File file = (File) child;
        return file.getContent();
    }

//    public FileSystemEntry getContents(String dirPath) {}


}
