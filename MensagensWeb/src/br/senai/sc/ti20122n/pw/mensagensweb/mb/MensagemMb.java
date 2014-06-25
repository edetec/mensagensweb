package br.senai.sc.ti20122n.pw.mensagensweb.mb;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.Part;

import br.senai.sc.ti20122n.pw.mensagensweb.model.entity.Mensagem;
import br.senai.sc.ti20122n.pw.mensagensweb.util.FacesContextUtil;
import br.senai.sc.ti20122n.pw.mensagensweb.util.ImagemUtil;

@ManagedBean
public class MensagemMb {

	private List<Mensagem> mensagens;
	private EntityManager entityManager;
	private Mensagem mensagem;
	private Part imagem;
	private String imagemAntiga;

	@PostConstruct
	private void init() {
		mensagem = new Mensagem();
		entityManager = FacesContextUtil.getEntityManager();
	}

	public String getCaminhoRelativo(String nomeImagem) {
		return ImagemUtil.getCaminhoRelativo(nomeImagem);
	}

	public List<Mensagem> getMensagens() {
		if (mensagens == null) {
			Query query = entityManager.createQuery("SELECT m FROM Mensagem m ",
					Mensagem.class);
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

	public String salvar() throws IOException {
		imagemAntiga = mensagem.getImagem();
		mensagem.setImagem(ImagemUtil.copiar(imagem,imagemAntiga));
		entityManager.merge(mensagem);
		return "mensagemlista";
	}

	public String editar(Long id) {
		mensagem = entityManager.find(Mensagem.class, id);
		return "mensagemform";
	}

	public String excluir(Long id) {
		Mensagem msg = entityManager.find(Mensagem.class, id);
		entityManager.remove(msg);
		ImagemUtil.deletar(mensagem.getImagem());
		mensagens = null;
		return "mensagemlista";
	}

	public Part getImagem() {
		return imagem;
	}

	public void setImagem(Part imagem) {
		this.imagem = imagem;
	}

	public String getImagemAntiga() {
		return imagemAntiga;
	}

	public void setImagemAntiga(String imagemAntiga) {
		this.imagemAntiga = imagemAntiga;
	}

}
