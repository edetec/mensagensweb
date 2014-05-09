package br.senai.sc.ti20122n.pw.mensagensweb.mb;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.senai.sc.ti20122n.pw.mensagensweb.model.entity.Mensagem;

@ManagedBean
public class MensagemMb {

	private List<Mensagem> mensagens;
	private EntityManager entityManager;

	private EntityManager getEntityManager() {
		return null;
	}

	public List<Mensagem> getMensagens() {
		if (mensagens == null) {
			Query query = getEntityManager().createQuery("SELECT m FROM Mensagem m",Mensagem.class);
			mensagens = query.getResultList();
		}
		return mensagens;
	}

	public void setMensagens(List<Mensagem> mensagens) {
		this.mensagens = mensagens;
	}

}
