# 🚌 Aiko Move SP

![Aiko](../imagens/movespread.png)

Bem-vindo ao Aiko Move SP! Este é um aplicativo Android desenvolvido em Kotlin utilizando Jetpack Compose, que fornece informações em tempo real sobre o transporte público na cidade de São Paulo.

📋 Sumário
- [🚀 Introdução](#introdução)
- [🛠️ Funcionalidades](#funcionalidades)
- [🎨  Decisões de Design](#decisões-de-design)
- [🏗️ Arquitetura](#arquitetura)
- [💻 Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [⚙️ Instalação](#instalação)
- [📖 Como Usar](#como-usar)
- [📚 Pessoa Desenvolvedora do Projeto](#-pessoa-desenvolvedora-do-projeto)

## 🚀 Introdução
O Aiko Move SP foi desenvolvido para auxiliar os usuários do transporte público de São Paulo a obter informações atualizadas sobre linhas de ônibus, paradas, previsões de chegada e posições dos veículos em tempo real.

## 🛠️ Funcionalidades
- 🗺️ Mapa Interativo: Visualize a localização em tempo real dos veículos no mapa.
- 🔍 Busca de Linhas: Pesquise linhas de ônibus por termos e visualize detalhes.
- 🛑 Busca de Paradas: Encontre paradas próximas ou específicas por meio de busca.
- ⏱️ Previsão de Chegada: Obtenha previsões de chegada dos ônibus em uma parada específica.
- ⚡ Atualização Automática: Dados atualizados automaticamente a cada 20 segundos.

## 🎨 Decisões de Design
- Arquitetura MVVM com Clean Architecture: Para manter o código organizado e facilitar a manutenção e testes.
- Uso de Hilt para Injeção de Dependência: Simplifica a injeção de dependências e melhora a escalabilidade.
- LiveData para Gerenciamento de Estados: Facilita a observação de dados e atualizações na UI.
- Jetpack Compose: Para construção de interfaces de usuário modernas e reativas.
- Retrofit e OkHttp: Para consumo de APIs RESTful de forma eficiente.
- Google Maps API: Para exibição de mapas e marcadores de veículos e paradas.

## 🏗️ Arquitetura
O aplicativo segue os princípios da Clean Architecture dividindo responsabilidades em:

- Data Layer: Repositórios que gerenciam a obtenção de dados das APIs.
- Domain Layer: Use Cases que contêm a lógica de negócio.
- Presentation Layer: ViewModels que fornecem dados para as telas e gerenciam o estado da UI.
- UI Layer: Composables que constroem a interface do usuário.

## 💻 Tecnologias Utilizadas

![Kotlin](https://img.shields.io/badge/kotlin-%237F52FF.svg?style=for-the-badge&logo=kotlin&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/jetpack%20compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white)

- Hilt (Injeção de Dependência)
- Retrofit & OkHttp (Consumo de API)
- Moshi (Parsing JSON)
- LiveData
- Google Maps API
- Coroutines
- Navigation Component

## ⚙️ Instalação
- Clone o repositório
- Abra o projeto no Android Studio.
- Obtenha as chaves de API:
  - SPTrans API Token: Registre-se em SPTrans API e obtenha seu token.
  - Google Maps API Key: Obtenha uma chave da Google Cloud Console.

- Configure as chaves:

  - Substitua o token no método authenticate em LoadingScreen.kt.
  - Substitua o valor de android:value no arquivo AndroidManifest.xml com sua API_KEY do Google Maps.

- Sincronize o projeto e execute o aplicativo em um dispositivo ou emulador Android.

## 📖 Como Usar
- Mapa:

  - Acesse a aba "Mapa" para visualizar os veículos em tempo real.
  - Os marcadores representam a localização atual dos ônibus.

- Linhas:

  - Na aba "Linhas", pesquise por termos para encontrar linhas específicas.
  - Clique em uma linha para ver detalhes adicionais (em desenvolvimento).

- Paradas:

  - Utilize a aba "Paradas" para buscar por paradas específicas.
  - As paradas serão exibidas no mapa com informações detalhadas.

- Previsão:

  - Insira o código de uma parada na aba "Previsão" para obter previsões de chegada dos ônibus.

# 📚 Pessoa Desenvolvedora do Projeto

[<img loading="lazy" style="border-radius: 50%;" src="https://avatars.githubusercontent.com/u/74202510?v=4" width=115><br><sub><b>Gabriel Felipe Barbosa</b></sub>](https://github.com/gabrielfbarbosa)

Feito com ❤️ por Gabriel Felipe Barbosa 👋🏽 Entre em contato!

![LinkedIn](https://img.shields.io/badge/Gabriel-%230077B5.svg?style=for-the-badge&logo=linkedin&logoColor=white)

[![Yahoo!](https://img.shields.io/badge/Yahoo!-gfelipebarbosa@yahoo.com-6001D2?style=for-the-badge&logo=Yahoo!&logoColor=white&link=mailto:gfelipebarbosa@yahoo.com)](mailto:gfelipebarbosa@yahoo.com)

