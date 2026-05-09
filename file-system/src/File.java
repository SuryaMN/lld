public class File extends FileSystemEntry {

    private String content;
    public File(String name, Directory parent, String content) {
        super(name, parent);
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
