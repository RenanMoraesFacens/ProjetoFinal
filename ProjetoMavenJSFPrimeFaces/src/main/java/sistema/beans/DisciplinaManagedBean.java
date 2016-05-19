package sistema.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.RowEditEvent;

import sistema.modelos.Aluno;
import sistema.modelos.Professor;
import sistema.modelos.Disciplina;
import sistema.service.ProfessorService;
import sistema.service.DisciplinaService;

@ManagedBean(name = "disciplinaManagedBean")
@ViewScoped
public class DisciplinaManagedBean {

	private Disciplina disciplina = new Disciplina();
	private Professor professor;
	private DisciplinaService prodService = new DisciplinaService();
	private ProfessorService fornService = new ProfessorService();
	private List<Disciplina> disciplinas;

	public void salvar() {
		professor.addDisciplina(disciplina);
		disciplina.setProfessor(professor);

		disciplina = prodService.salvar(disciplina);

		if (disciplinas != null)
			disciplinas.add(disciplina);

		disciplina = new Disciplina();
		professor = null;

	}

	public List<Professor> getProfessores() {
		return fornService.getProfessores();

	}

	public Professor getProfessor() {
		return professor;
	}

	public void remove(Disciplina disciplina) {
		prodService.remover(disciplina);
		disciplinas.remove(disciplina);
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public List<Disciplina> getDisciplinas() {
		if (disciplinas == null)
			disciplinas = prodService.getDisciplinas();

		return disciplinas;
	}

	public void onRowEdit(RowEditEvent event) {

		Disciplina p = ((Disciplina) event.getObject());
		prodService.alterar(p);
	}

}
