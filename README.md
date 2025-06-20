# 🧠 Medlink - Plataforma para suas consultas onlines ou presenciais

**Medlink** é uma aplicação web desenvolvida para facilitar o agendamento de consultas online e presenciais entre pacientes e psicólogos. A plataforma visa oferecer praticidade, segurança e confiança no processo de busca e marcação de atendimentos psicológicos.

---

## 🌐 Funcionalidades

- 📅 Agendamento de consultas **online** ou **presenciais**
- 👤 Registro simples para pacientes (Nome, E-mail, Senha)
- 🧑‍⚕️ Registro de psicólogos com validação de **CRM profissional**
- 🔎 Acesso dos pacientes ao perfil completo do psicólogo antes de agendar
- 🔐 Autenticação segura para ambos os tipos de usuários
- 💬 Transparência: psicólogos podem compartilhar conteúdos, especializações e experiências

---

## 🛠️ Tecnologias Utilizadas

- **Frontend**: React.js / Vue.js / (adicione aqui a tecnologia usada)
- **Backend**: Node.js / Django / (adicione aqui a tecnologia usada)
- **Banco de Dados**: PostgreSQL / MongoDB / (adicione aqui)
- **Autenticação**: JWT / OAuth / (conforme aplicável)
- **Validação de CRM**: (Explique se há integração com alguma API oficial)

---

## 👥 Tipos de Usuários

### 🧑 Paciente
- Cadastro com Nome, E-mail e Senha
- Pode visualizar todos os perfis de psicólogos cadastrados
- Pode agendar consultas online ou presenciais
- Pode analisar o conteúdo dos psicólogos para escolher o mais adequado

### 🧑‍⚕️ Psicólogo
- Cadastro com Nome, E-mail, Senha e número do **CRM**
- CRM é verificado para garantir autenticidade
- Pode criar e atualizar seu perfil com:
  - Abordagens terapêuticas
  - Formação e especializações
  - Experiência profissional
  - Artigos, vídeos, links e depoimentos

---

## 🚧 Em Desenvolvimento

- Notificações por e-mail e/ou push
- Integração com plataformas de videochamada
- Sistema de avaliação de psicólogos
- Dashboard para gerenciar agendamentos

---

## 📦 Como rodar o projeto localmente

```bash
# Clone este repositório
git clone https://github.com/Pedro-prg/consulta-facil.git

# Acesse a pasta do projeto
cd Medlink

# Instale as dependências
npm install

# Inicie o servidor
npm run dev
