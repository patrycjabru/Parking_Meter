package database;

interface DAO<T> {
    void add(T t);
    void get(T t);
    void delete(T t);
}
