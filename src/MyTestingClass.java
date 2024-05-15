public class MyTestingClass {
    int id;

    public MyTestingClass(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyTestingClass that = (MyTestingClass) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        return (id ^ (id >>> 16)) * prime;
    }
}