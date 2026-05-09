import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Directory extends FileSystemEntry {

    private Map<String, FileSystemEntry> children;
    public Directory(String name, Directory parent) {
        super(name, parent);
        this.children = new HashMap<>();
    }

    public Map<String, FileSystemEntry> getChildren() {
        return children;
    }

    public void setChildren(Map<String, FileSystemEntry> children) {
        this.children = children;
    }

    public boolean containsChild(String name) {
        return children.containsKey(name);
    }

    public Optional<FileSystemEntry> getChild(String dirName) {
        return Optional.ofNullable(children.get(dirName));
    }

    public void createDirectory(String name) {
        Directory dir = new Directory(name, this);
        this.children.put(name, dir);
    }

    public void createFile(String name, String content) {
        File file  = new File(name, this, content);
        this.children.put(name, file);
    }

    public void removeChild(String name) {
        this.children.remove(name);
    }

    public void addChild(FileSystemEntry sourceLeafNode) {
        if(this.children.containsKey(sourceLeafNode.getName())) {
            throw new RuntimeException("Directory or file with this name exists already");
        }
        this.children.put(sourceLeafNode.getName(), sourceLeafNode);
    }
}
