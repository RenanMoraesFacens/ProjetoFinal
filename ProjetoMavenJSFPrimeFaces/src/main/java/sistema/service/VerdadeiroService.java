package sistema.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import sistema.dao.VerdadeiroDAO;
import sistema.modelos.Verdadeiro;

public class VerdadeiroService {

	VerdadeiroDAO verdadeiroDAO = new VerdadeiroDAO();

	public Verdadeiro salvar(Verdadeiro verdadeiro) {
		verdadeiro = verdadeiroDAO.save(verdadeiro);
		verdadeiroDAO.closeEntityManager();
		return verdadeiro;

	}

	public List<Verdadeiro> getVerdadeiros() {
		List<Verdadeiro> list = verdadeiroDAO.getAll(Verdadeiro.class);
		verdadeiroDAO.closeEntityManager();
		return list;
	}

	public void alterar(Verdadeiro verdadeiro) {
		verdadeiroDAO.save(verdadeiro);
		verdadeiroDAO.closeEntityManager();
	}

	public void remover(Verdadeiro verdadeiro) {

		verdadeiro = verdadeiroDAO.getById(Verdadeiro.class, verdadeiro.getCodigo());
		verdadeiroDAO.remove(verdadeiro);
		verdadeiroDAO.closeEntityManager();
	}

}
