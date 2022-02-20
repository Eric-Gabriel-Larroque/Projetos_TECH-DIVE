# Projeto 2 - HabilitiPRO

> Projeto focado no cadastro e gerenciamento de entidades para simulação de um projeto real

## Descrição e Estruturação do Projeto:

![](img/img.png)

Projeto composto com diversas funcionalidades, como:


### 1. **Cadastro de**:
- **Empresas**;
- **Trabalhadores**;
- **Trilhas** para cada **Empresa**;
- **Módulos** para cada **Trilha**;
- **Usuários** e seus perfis de acesso;

### 2. Registros de Trabalhador:

- Para cada instância de **Trabalhador**;
- Cada vez que há uma mudança em seu atributo _Setor/Área_;
- Cada vez que há mudança em seu atributo _Função_; e
- A cada mudança em seu atributo _Empresa_;
- A cada início e término de um Módulo;

### 3. Validação:

- Textual;
- Numérico;
- Para o formato de **CPF**;
- Para o formato de **CNPJ**;
- Para uma senha de acesso com caracteres alfanuméricos;
- Para formato de email válido

## Classes que compões o projeto:

O Projeto é organizado em diversas pastas para cada tipo de classe ou sua competência. 

Em síntese, as classes que a compõem são as seguintes:

- **Enums:**
  - **Perfis:** Onde armazenam os Perfis de Acesso possíveis para o usuário;
  - **Regionais:** Compõem as Regionais do SENAI para popular o atributo _regional_ da entidade **Empresa**;
  - **Segmento:** Composto por um número limitado de segmentos paraa população do atributo _segmento_ da entidade **Empresa**;

- **Modelos**  - Entidades responsáveis em suma pela própria população de seus atributo e/ou sua validação. É composto pelas entidades:
  - **Empresa**;
  - **Trabalhador**;
  - **Trilha**;
  - **Módulo**;
  - **Usuário**
- **Util** - Classes com utilidade para além de seu pacote e com presençaa em quase todo projeto. Contém as seguintes Classes:
  - **DateHandler** - Classe criada com o intuito de formataçaõ de datas no formato LocalDate e OffSetDateTime;
  - **FileHandler** - Classe responsável pelo registro das Mudançs de atributos e status de Módulos em arquivo de texto;
  - **Validacao** - Classe responsável pela validaçãao de dados alfanuméricos, de listas vazias e de templates específicas;
- **Misc** - Contém o controle de registro de:
  - CPF; e
  - CNPJ
- **Main** - Classe principal para execução do projeto e para fins de testes de funcionalidade

> Com o intuito de simular entidades separadas das classes Modelo e validar a existência de um mesmo registro para todas as entidades que contém o mesmo tipo de registro
## Especificidades do Projeto:

- Cada **Trilha** deve ter somente uma **Empresa**;
- Cada **Módulo** deve ter pelo menos uma **Trilha**;
- A **Trilha não pode ser instanciada** sem a existência anterior
de uma **Empresa**, como também um **Módulo** não pode existir 
sem preceder de uma **Trilha**.
- O atributo _status_ da entidade **Módulo** deve ser modificado de acordo com a data Inicial e final do projeto, além da data final para avaliação do desempenho do Trabalhador e quando ainda não se iniciou o módulo;
- O **Trabalhador**, também, não pode ser instanciado sem antes a existência de, no mínimo, 1 **Módulo**, **Trilha** e **Empresa** para se vincular.