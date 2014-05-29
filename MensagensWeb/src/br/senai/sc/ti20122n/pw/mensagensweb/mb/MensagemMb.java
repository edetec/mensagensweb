package br.senai.sc.ti20122n.pw.mensagensweb.mb;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.senai.sc.ti20122n.pw.mensagensweb.model.entity.Mensagem;
import br.senai.sc.ti20122n.pw.mensagensweb.util.FacesContextUtil;

@ManagedBean
public class MensagemMb {

	private List<Mensagem> mensagens;
	private Mensagem mensagem;
	private EntityManager entityManager;

	@PostConstruct
	private void init() {
		mensagem = new Mensagem();
		entityManager = FacesContextUtil.getEntityManager();
	}

	public List<Mensagem> getMensagens() {
		if (mensagens == null) {
			Query query = entityManager.createQuery(
					"SELECT m FROM Mensagem m", Mensagem.class);
			mensagens = query.getResultList();
		}
		return mensagens;
	}

	public void setMensagens(List<Mensagem> mensagens) {
		this.mensagens = mensagens;
	}

	public Mensagem getMensagem() {
		return mensagem;
	}

	public void setMensagem(Mensagem mensagem) {
		this.mensagem = mensagem;
	}
	
	public String salvar(){
		entityManager.merge(mensagem);
		
		return "mensagemlista";
	}
	
	public String editar(Long id){
		mensagem = entityManager.find(Mensagem.class, id);
		return "mensagemform";
	}
	
	public String excluir(Long id){
		Mensagem msg = entityManager.find(Mensagem.class, id);
		entityManager.remove(msg);
		return "mensagemlista";
	}

}
