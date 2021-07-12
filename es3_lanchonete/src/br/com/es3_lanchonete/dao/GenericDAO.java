package br.com.es3_lanchonete.dao;

public interface GenericDAO <T> {
    public int insert(T obj);
    public java.util.List<T> listAll();
    public int update(T obj);
    public int delete(T obj);
    public T findByID(int id);
}

