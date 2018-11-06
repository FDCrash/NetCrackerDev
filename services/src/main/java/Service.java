public interface Service<T> {
    public void addNew(T t);
    public void deleteInfo(int id);
    public void updateInfo(T t,int id);
}
