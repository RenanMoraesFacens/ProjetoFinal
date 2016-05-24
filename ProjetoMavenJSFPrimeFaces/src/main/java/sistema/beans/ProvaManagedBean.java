package sistema.beans;

import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.RowEditEvent;

import sistema.modelos.Conteudo;
import sistema.modelos.Disciplina;
import sistema.modelos.Pergunta;
import sistema.modelos.Prova;
import sistema.service.DisciplinaService;
import sistema.service.ProvaService;

@ManagedBean(name = "provaManagedBean")
@ViewScoped
public class ProvaManagedBean {
	

	private Prova prova = new Prova();
	private Disciplina disciplina;
	private ProvaService prodService = new ProvaService();
	private DisciplinaService fornService = new DisciplinaService();
	private List<Prova> provas;
	Calendar c = Calendar.getInstance();
	double tempototal = 0;
	
	public void salvar() {
		
		disciplina.addProva(prova);
		prova.setDisciplina(disciplina);
		
		for(Conteudo cont: disciplina.getConteudos())
		{
			for(Pergunta p: cont.getPerguntas())
			{
		        prova.addPergunta(p);
				prova.ConcatenaString("pergunta");
				tempototal = tempototal + p.getTempo();
			}
		}

		prova.setTempo(tempototal);
		tempototal = 0;
		
		prova.setDataAplicacao(c.getTime());
		
		
		
		prova = prodService.salvar(prova);

		if (provas != null)
			provas.add(prova);
		
		
		
		prova = new Prova();
		disciplina = null;

	}

	public List<Disciplina> getDisciplinas() {
		return fornService.getDisciplinas();

	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void remove(Prova prova) {
		prodService.remover(prova);
		provas.remove(prova);
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Prova getProva() {
		return prova;
	}

	public void setProva(Prova prova) {
		this.prova = prova;
	}

	public List<Prova> getProvas() {
		if (provas == null)
			provas = prodService.getProvas();

		return provas;
	}

	public void onRowEdit(RowEditEvent event) {

		Prova p = ((Prova) event.getObject());
		prodService.alterar(p);
	}

}