import java.util.Objects;

public class File {
    private String fileName;
    private String fileType;

    public File(String fileName, String fileType) throws IllegalArgumentException {
        setFileName(fileName);
        setFileType(fileType);
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) throws IllegalArgumentException {
        if (fileName == null || fileName.trim().isEmpty()) {
            throw new IllegalArgumentException("File name cannot be empty.");
        }
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) throws IllegalArgumentException {
        if (fileType == null || fileType.trim().isEmpty()) {
            throw new IllegalArgumentException("File type cannot be empty.");
        }
        this.fileType = fileType;
    }

    @Override
    public String toString() {
        return fileName + "." + fileType;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof File)) return false;
        File other = (File) obj;
        return Objects.equals(fileName, other.fileName) && Objects.equals(fileType, other.fileType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileName, fileType);
    }
}
