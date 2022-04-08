package br.com.alura.forum.config;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.alura.forum.modelo.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${forum.jwt.expiration}")
	private String expiration;
	
	@Value("${forum.jwt.secret}")
	private String secret;
	
	public String gerarToken(Authentication authentication) {
		Usuario logado = (Usuario) authentication.getPrincipal();
		Date data = new Date();
		Date dataExpiracao = new Date(data.getTime() + Long.parseLong(expiration));
		
		return Jwts.builder().setIssuer("api do forum")
				.setSubject(logado.getId().toString())//passa o usuario
				.setIssuedAt(data)
				.setExpiration(dataExpiracao)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

	public boolean isTokenValido(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);//claims ja devolve uma exception caso ocorre um erro
			return true;
		} catch (Exception e) {
			return false;	
		}
		
		
	}

	public Long getIdUsuario(String token) {
		//foi passado o usuario na hora da criacao do token
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());//pega o usuario
	}

	
	
}
