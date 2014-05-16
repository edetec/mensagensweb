package br.senai.sc.ti20122n.pw.mensagensweb.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import br.senai.sc.ti20122n.pw.mensagensweb.model.entity.Mensagem;
import br.senai.sc.ti20122n.pw.mensagensweb.util.FacesContextUtil;

@ManagedBean
public class MensagemMb {

	private List<Mensagem> mensagens;
	private EntityManager entityManager;

	@PostConstruct
	private void init() {
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

}
