package habilitipro.modelos;

import habilitipro.enums.Perfis;
import habilitipro.misc.CPF;

import javax.swing.*;

import static habilitipro.util.Validacao.*;

import java.util.*;

public class Usuario {

    private String nome;
    private CPF CPF;
    private String email;
    private String senha;
    private LinkedHashSet<Perfis> perfisDeAcesso = new LinkedHashSet<>();

    public Usuario() {
        setNome();
        setCPF();
        setEmail();
        setSenha();
        setPerfisDeAcesso();
    }

    private void setNome() {
        this.nome = validaString("Insira seu nome:");
    }
    private void setCPF() {
        this.CPF = new CPF();
    }
    private void setEmail() {
        final String EMAIL_TEMPLATE = "^[a-zA-Z0-9]+@[a-zA-Z]+.+[a-zA-Z]";
        this.email = validaString("Insira seu email",EMAIL_TEMPLATE,
                "Formato de email inválido. Deve seguir o formato usuario@dominio.terminacao");
    }

    private void setSenha() {
        final String SENHA_TEMPLATE = "^[a-zA-Z0-9]+";
        while(this.senha==null||this.senha.length()<8){

            this.senha = validaString(
                    "Insira sua senha (deve conter pelo menos 8 caracteres, contendo letras e números)"
            ,SENHA_TEMPLATE,"A senha só pode conter letras e/ou números");

            if(this.senha.length()<8){
                JOptionPane.showMessageDialog(null,
                        "A senha não pode conter menos de 8 caracteres"
                );
            }
        }
    }

    private void setPerfisDeAcesso() {
        String[] opcoes = {"Sim","Não"};
        List<String> listaPerfisNome = new ArrayList<>();
        String perfisDoUsuario = "";
        Arrays.stream(Perfis.values()).toList().forEach(p->listaPerfisNome.add(p.getPerfilDeAcesso()));
        int resposta = -1;
        while(resposta!=1){
            int perfilEscolhido = escolherOpcoes("Qual é o seu perfil de acesso?",
                    listaPerfisNome.toArray());
            perfisDeAcesso.add(Arrays.stream(Perfis.values()).toList().get(perfilEscolhido));
            resposta = escolherOpcoes("Gostaria de adicionar mais perfis de acesso?",opcoes);
        }
        for(Perfis perfil: perfisDeAcesso){
            perfisDoUsuario+="\n- "+ perfil.getPerfilDeAcesso();
        }
        JOptionPane.showMessageDialog(null,
                this.nome+" contém os seguintes perfis de acesso:"+perfisDoUsuario);
    }
}