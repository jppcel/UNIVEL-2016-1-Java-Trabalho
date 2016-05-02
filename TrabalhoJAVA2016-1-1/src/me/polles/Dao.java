package me.polles;

import java.util.List;

public interface Dao<T, K> {

	public void salvar(T t);
	
	public T buscar(K k);
	
	public void atualizar(T t);
	
	public void excluir(K k);
	
	public void drop(T t);
	
	public void criarTabela(T t);
	
	public List<T> listarTodos();

}
