package br.senai.sc.ti20122n.pw.mensagensweb.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;

public class ImagemUtil {
	private static final String DIRETORIO_IMG_UPLOADS = "/resources/img/uploads/";
	public static final Map<String, String> TIPOS = new HashMap<String, String>();
	public static final long TAMANHO_MAXIMO = 1024 * 1024;

	static {
		TIPOS.put("image/jpeg", "jpg");
		TIPOS.put("image/png", "png");
		TIPOS.put("image/gif", "gif");
	}

	public static String getCaminhoRelativo(String nomeImagem) {
		return DIRETORIO_IMG_UPLOADS.concat(nomeImagem);
	}

	public static void deletar(String nomeImagem) {
		if (nomeImagem == null)
			return;

		File file = new File(ImagemUtil.getCaminhoAbsoluto(nomeImagem));
		if (file.exists())
			file.delete();
	}

	public static String getCaminhoAbsoluto(String nomeImagem) {
		String relativeWebPath = DIRETORIO_IMG_UPLOADS.concat(nomeImagem);
		ServletContext servletContext = (ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext();
		String absoluteDiskPath = servletContext.getRealPath(relativeWebPath);
		return absoluteDiskPath;
	}

	public static String copiar(Part imagem, String imagemAntiga)
			throws IOException {
		if (imagem == null)
			return imagemAntiga;

		String nomeImagem = gerarNomeImagem(imagem);

		imagem.write(getCaminhoAbsoluto(nomeImagem));
		deletar(imagemAntiga);
		return nomeImagem;
	}

	public static String copiarParaTamanho(Part imagem, String imagemAntiga,
			int largura, int altura) throws IOException {
		if (imagem == null)
			return imagemAntiga;

		String nomeImagem = gerarNomeImagem(imagem);

		BufferedImage imagemOriginal = ImageIO.read(imagem.getInputStream());
		BufferedImage imagemRedimensionada = new BufferedImage(largura, altura,
				BufferedImage.TYPE_INT_RGB);

		imagemRedimensionada.getGraphics().drawImage(imagemOriginal, 0, 0,
				largura, altura, null);

		File destino = new File(getCaminhoAbsoluto(nomeImagem));
		
		ImageIO.write(imagemRedimensionada, getExtensao(imagem), destino);

		deletar(imagemAntiga);
		return nomeImagem;
	}

	private static String getExtensao(Part imagem) {
		return TIPOS.get(imagem.getContentType());
	}

	private static String gerarNomeImagem(Part imagem) {
		if (!TIPOS.containsKey(imagem.getContentType()))
			return null;

		String extensao = ".".concat(getExtensao(imagem));
		String nome = UUID.randomUUID().toString();
		return nome.concat(extensao);
	}
}
