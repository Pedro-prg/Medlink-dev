# Medlink

Medlink é um sistema web para agendamento de consultas médicas, desenvolvido para facilitar a interação entre pacientes e profissionais de saúde. O projeto foi construído com Java, Spring Boot, Thymeleaf e MySQL, seguindo práticas modernas de desenvolvimento ágil.

## ✨ Visão Geral
O Medlink permite que pacientes agendem consultas com médicos de diferentes especialidades, visualizem histórico de atendimentos e participem de reuniões online. Médicos podem gerenciar sua disponibilidade, editar seu perfil e acompanhar o histórico de consultas realizadas.

## 🚀 Funcionalidades Principais
- Cadastro e autenticação de pacientes e médicos
- Edição de perfil médico (especialidades, experiências, trabalhos passados)
- Gerenciamento de disponibilidade de horários pelos médicos
- Agendamento de consultas pelos pacientes
- Visualização de histórico de consultas (separado para médicos e pacientes)
- Acesso a salas de reunião online para telemedicina
- Visualização do perfil do médico pelo paciente

## 🛠️ Tecnologias Utilizadas
- **Backend:** Java, Spring Boot
- **Frontend:** Thymeleaf, HTML5, CSS3
- **Banco de Dados:** MySQL
- **ORM:** Spring Data JPA
- **Template Engine:** Thymeleaf
- **Controle de Versão:** Git

## 📁 Estrutura do Projeto
```
Medlink/
├── src/
│   └── main/
│       ├── java/phenrique/com/A3/Medlink/...
│       └── resources/
│           ├── templates/
│           └── db/migration/
├── pom.xml
├── README.md
└── ...
```

## ⚙️ Como Executar o Projeto
1. **Clone o repositório:**
   ```bash
   git clone https://github.com/Pedro-prg/medlink.git
   ```
2. **Configure o banco de dados MySQL:**
   - Crie um banco de dados chamado `medlink`.
   - Atualize as credenciais no arquivo `src/main/resources/application.properties`.
3. **Execute as migrações:**
   - O Flyway executará as migrações automaticamente ao iniciar o projeto.
4. **Compile e rode o projeto:**
   ```bash
   ./mvnw spring-boot:run
   ```
5. **Acesse no navegador:**
   - [http://localhost:8080](http://localhost:8080)

## 🧑‍💻 Contribuição
Contribuições são bem-vindas! Siga os passos abaixo:
1. Fork este repositório
2. Crie uma branch para sua feature (`git checkout -b minha-feature`)
3. Commit suas alterações (`git commit -m 'feat: minha nova feature'`)
4. Push para a branch (`git push origin minha-feature`)
5. Abra um Pull Request

## 📄 Licença
Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## 📬 Contato
Dúvidas, sugestões ou feedback? Entre em contato:
- [Pedro Henrique](pedro17gledistony@gmail.com)

---

> Projeto desenvolvido para a disciplina de Engenharia de Software - [UNP].
