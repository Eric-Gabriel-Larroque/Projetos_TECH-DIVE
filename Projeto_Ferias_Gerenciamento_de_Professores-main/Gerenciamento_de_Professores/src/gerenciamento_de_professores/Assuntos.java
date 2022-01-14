package gerenciamento_de_professores;

public enum Assuntos {

   CMD("Comandos de terminal com BASH e PowerShell"),
   SQL("Banco de Dados SQL"),
   MONGO("Banco de dados não relacional com MongoDB"),
   CSHARP("Linguagem de programação C#"),
   JAVA("lógica de programação+POO com Java"),
   JAVASCRIPT("Desenvolvimento web com Javascript+HTML+CSS"),
   NODE("Backend com Node.js"),
   GIT("Versionamento de Código com Git"),
   SOFTSKILLS("Treinamento de softskills"),
   SCRUM("Metodologia ágil SCRUM+Kanban"),
   LGPD("Estudos sobre a Lei Geral de Proteção de Dados"),
   CLOUD("Computação em nuvem (AWS,GCP,AZURE e OCI)");

    private final String nomeAssunto;

    Assuntos(String nome) {
        this.nomeAssunto = nome;
        toString();
    }

    public String getNomeAssunto() {
        return this.nomeAssunto;
    }
}
