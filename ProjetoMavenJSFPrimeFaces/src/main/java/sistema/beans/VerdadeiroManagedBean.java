package sistema.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.RowEditEvent;

import sistema.modelos.Conteudo;
import sistema.modelos.Verdadeiro;
import sistema.service.ConteudoService;
import sistema.service.VerdadeiroService;

@ManagedBean(name = "verdadeiroManagedBean")
@ViewScoped
public class VerdadeiroManagedBean {

	private Verdadeiro verdadeiro = new Verdadeiro();
	private Conteudo conteudo;
	private VerdadeiroService prodService = new VerdadeiroService();
	private ConteudoService fornService = new ConteudoService();
	private List<Verdadeiro> verdadeiros;

	public void salvar() {
		verdadeiro.setTipo("Verdadeiro/Falso");
		conteudo.addPergunta(verdadeiro);
		verdadeiro.setConteudo(conteudo);

		verdadeiro = prodService.salvar(verdadeiro);

		if (verdadeiros != null)
			verdadeiros.add(verdadeiro);

		verdadeiro = new Verdadeiro();
		conteudo = null;

	}

	public List<Conteudo> getConteudos() {
		return fornService.getConteudos();

	}

	public Conteudo getConteudo() {
		return conteudo;
	}

	public void remove(Verdadeiro verdadeiro) {
		prodService.remover(verdadeiro);
		verdadeiros.remove(verdadeiro);
	}

	public void setConteudo(Conteudo conteudo) {
		this.conteudo = conteudo;
	}

	public Verdadeiro getVerdadeiro() {
		return verdadeiro;
	}

	public void setVerdadeiro(Verdadeiro verdadeiro) {
		this.verdadeiro = verdadeiro;
	}

	public List<Verdadeiro> getVerdadeiros() {
		if (verdadeiros == null)
			verdadeiros = prodService.getVerdadeiros();

		return verdadeiros;
	}

	public void onRowEdit(RowEditEvent event) {

		Verdadeiro p = ((Verdadeiro) event.getObject());
		prodService.alterar(p);
	}

}
